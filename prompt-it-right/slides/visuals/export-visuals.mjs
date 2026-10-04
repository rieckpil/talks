// Exports the Germany map canvas scene to PNG files in ../assets.
// Usage (from the talk folder or from slides/): node slides/visuals/export-visuals.mjs
// Uses `playwright` when it is resolvable, otherwise the `puppeteer-core` bundled
// with the global Marp CLI plus Google Chrome (override with CHROME_PATH).
import { createRequire } from 'node:module';
import { execSync } from 'node:child_process';
import path from 'node:path';
import fs from 'node:fs';
import { fileURLToPath, pathToFileURL } from 'node:url';

const hereDir = path.dirname(fileURLToPath(import.meta.url));
const assetsDir = path.resolve(hereDir, '..', 'assets');
const deviceScaleFactor = Number(process.env.SCALE || 2);

const scenes = [
  { page: 'germany-map.html', query: '', file: 'germany-erlangen-munich.png', width: 420, height: 720 },
  { page: 'abstract-blue.html', query: '', file: 'abstract-blue-left.png', width: 422, height: 720 },
  { page: 'context-diagrams.html', query: 'view=typical', file: 'context-v2-typical.png', width: 1920, height: 1080 },
  { page: 'context-diagrams.html', query: 'view=colored', file: 'context-v2-colored.png', width: 1920, height: 1080 },
  { page: 'context-diagrams.html', query: 'view=sliced', file: 'context-v2-sliced.png', width: 1920, height: 1080 },
  { page: 'context-diagrams.html', query: 'view=webmvc', file: 'context-v2-webmvc.png', width: 1920, height: 1080 }
];

async function launchBrowser() {
  const localRequire = createRequire(import.meta.url);
  try {
    const { chromium } = localRequire('playwright');
    const browser = await chromium.launch();
    return { kind: 'playwright', browser };
  } catch (error) {
    // fall through to puppeteer-core
  }
  const globalRoot = execSync('npm root -g', { encoding: 'utf8', stdio: ['ignore', 'pipe', 'ignore'] }).trim();
  const marpRequire = createRequire(path.join(globalRoot, '@marp-team', 'marp-cli', 'package.json'));
  const puppeteer = marpRequire('puppeteer-core');
  const executablePath = process.env.CHROME_PATH || '/Applications/Google Chrome.app/Contents/MacOS/Google Chrome';
  const browser = await puppeteer.launch({ executablePath, headless: true });
  return { kind: 'puppeteer', browser };
}

async function exportScene({ kind, browser }, scene) {
  const outputPath = path.join(assetsDir, scene.file);
  let page;
  if (kind === 'playwright') {
    const context = await browser.newContext({ viewport: { width: scene.width, height: scene.height }, deviceScaleFactor });
    page = await context.newPage();
  } else {
    page = await browser.newPage();
    await page.setViewport({ width: scene.width, height: scene.height, deviceScaleFactor });
  }
  const sceneUrl = pathToFileURL(path.join(hereDir, scene.page)).href;
  await page.goto(`${sceneUrl}?${scene.query}`, { waitUntil: 'networkidle0' }).catch(() => page.goto(`${sceneUrl}?${scene.query}`));
  await page.waitForFunction(() => window.__rendered === true, { timeout: 30000 });
  const canvasHandle = await page.$('canvas');
  await canvasHandle.screenshot({ path: outputPath, omitBackground: true });
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
