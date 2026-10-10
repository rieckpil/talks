import { Config } from '@remotion/cli/config';

Config.setVideoImageFormat('png');
Config.setOverwriteOutput(true);

// Remotion's own Chrome download stalls here, and the path contains a space
// which does not survive the npm script, so point at the installed browser.
Config.setBrowserExecutable(
  process.env.CHROME_PATH ?? '/Applications/Google Chrome.app/Contents/MacOS/Google Chrome'
);
