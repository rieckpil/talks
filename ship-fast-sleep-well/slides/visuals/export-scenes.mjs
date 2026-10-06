// Exports the hand-drawn scenes in scenes.html to PNG files in ../assets.
// Usage: node visuals/export-scenes.mjs   (from the slides folder)
// Uses the puppeteer-core bundled with the global Marp CLI plus Google Chrome (override with CHROME_PATH).
import { createRequire } from 'node:module';
import { execSync } from 'node:child_process';
import path from 'node:path';
import { fileURLToPath, pathToFileURL } from 'node:url';

const hereDir = path.dirname(fileURLToPath(import.meta.url));
const assetsDir = path.resolve(hereDir, '..', 'assets');
const sceneFileUrl = pathToFileURL(path.join(hereDir, 'scenes.html')).href;
const scenes = [
  { view: 'horror', file: 'horror-friday.png' },
  { view: 'target', file: 'target-state.png' },
  { view: 'canary', file: 'canary-mine.png', width: 900 }
];

const globalRoot = execSync('npm root -g', { encoding: 'utf8', stdio: ['ignore', 'pipe', 'ignore'] }).trim();
const marpRequire = createRequire(path.join(globalRoot, '@marp-team', 'marp-cli', 'package.json'));
const puppeteer = marpRequire('puppeteer-core');
const executablePath = process.env.CHROME_PATH || '/Applications/Google Chrome.app/Contents/MacOS/Google Chrome';
const browser = await puppeteer.launch({ executablePath, headless: true });

for (const { view, file, width = 1920 } of scenes) {
  const page = await browser.newPage();
  await page.setViewport({ width, height: 1080, deviceScaleFactor: 1 });
  await page.goto(`${sceneFileUrl}?view=${view}`, { waitUntil: 'networkidle0' });
  await page.evaluate(() => document.fonts.ready);
  await page.screenshot({ path: path.join(assetsDir, file) });
  console.log('wrote', file);
  await page.close();
}
await browser.close();
