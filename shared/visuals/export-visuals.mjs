// Exports HTML/canvas scenes of a deck to PNG files in shared/assets.
// Usage: node shared/visuals/export-visuals.mjs <deck-dir>
// The deck provides slides/visuals/scenes.mjs with `export const scenes = [...]`:
//   { page, query, file, width, height, selector?, waitRendered?, scale? }
//   file          output path below shared/assets, e.g. 'my-talk/map.png'
//   page          HTML file in slides/visuals (default scenes.html)
//   query         query string appended to the page URL
//   selector      element to screenshot (default: the whole page)
//   waitRendered  wait for `window.__rendered === true` before the screenshot
//   scale         device scale factor (default env SCALE or 2)
// Uses `playwright` when resolvable, otherwise the `puppeteer-core` bundled with the
// global Marp CLI plus Google Chrome (override with CHROME_PATH).
import { createRequire } from 'node:module';
import { execSync } from 'node:child_process';
import path from 'node:path';
import fs from 'node:fs';
import { pathToFileURL } from 'node:url';

const deckArg = process.argv[2];
if (!deckArg) {
  console.error('Usage: node shared/visuals/export-visuals.mjs <deck-dir>');
  process.exit(1);
}
let slidesDir = path.resolve(deckArg);
if (fs.existsSync(path.join(slidesDir, 'slides'))) slidesDir = path.join(slidesDir, 'slides');
const visualsDir = path.join(slidesDir, 'visuals');
const assetsDir = path.resolve(path.dirname(new URL(import.meta.url).pathname), '..', 'assets');
const { scenes } = await import(pathToFileURL(path.join(visualsDir, 'scenes.mjs')).href);
const defaultScale = Number(process.env.SCALE || 2);

async function launchBrowser() {
  const localRequire = createRequire(import.meta.url);
  try {
    const { chromium } = localRequire('playwright');
    return { kind: 'playwright', browser: await chromium.launch() };
  } catch (error) {
    // fall through to puppeteer-core
  }
  const globalRoot = execSync('npm root -g', { encoding: 'utf8', stdio: ['ignore', 'pipe', 'ignore'] }).trim();
  const marpRequire = createRequire(path.join(globalRoot, '@marp-team', 'marp-cli', 'package.json'));
  const puppeteer = marpRequire('puppeteer-core');
  const executablePath = process.env.CHROME_PATH || '/Applications/Google Chrome.app/Contents/MacOS/Google Chrome';
  return { kind: 'puppeteer', browser: await puppeteer.launch({ executablePath, headless: true }) };
}

async function exportScene({ kind, browser }, scene) {
  const { page: pageFile = 'scenes.html', query = '', file, width = 1920, height = 1080, selector, waitRendered = false } = scene;
  const deviceScaleFactor = scene.scale ?? defaultScale;
  const url = `${pathToFileURL(path.join(visualsDir, pageFile)).href}${query ? `?${query}` : ''}`;
  const outputPath = path.join(assetsDir, file);
  fs.mkdirSync(path.dirname(outputPath), { recursive: true });
  let page;
  if (kind === 'playwright') {
    const context = await browser.newContext({ viewport: { width, height }, deviceScaleFactor });
    page = await context.newPage();
  } else {
    page = await browser.newPage();
    await page.setViewport({ width, height, deviceScaleFactor });
  }
  await page.goto(url, { waitUntil: 'networkidle0' }).catch(() => page.goto(url));
  await page.evaluate(() => document.fonts.ready);
  if (waitRendered) await page.waitForFunction(() => window.__rendered === true, { timeout: 30000 });
  const target = selector ? await page.$(selector) : page;
  await target.screenshot({ path: outputPath, omitBackground: Boolean(selector) });
  console.log(`exported ${path.relative(process.cwd(), outputPath)}`);
  if (kind === 'playwright') await page.context().close();
  else await page.close();
}

fs.mkdirSync(assetsDir, { recursive: true });
const session = await launchBrowser();
console.log(`using ${session.kind}`);
try {
  for (const scene of scenes) await exportScene(session, scene);
} finally {
  await session.browser.close();
}
