// Hand-drawn scenes, exported with: node shared/visuals/export-visuals.mjs ship-fast-sleep-well
const base = { page: 'scenes.html', scale: 1, height: 1080 };
export const scenes = [
  { ...base, query: 'view=horror', file: 'horror-friday.png' },
  { ...base, query: 'view=target', file: 'target-state.png' },
  { ...base, query: 'view=canary', file: 'canary-mine.png', width: 900 }
];
