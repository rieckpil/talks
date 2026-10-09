// Canvas scenes, exported with: node shared/visuals/export-visuals.mjs prompt-it-right
const base = { selector: 'canvas', waitRendered: true };
export const scenes = [
  { ...base, page: 'germany-map.html', file: 'germany-herzogenaurach-munich.png', width: 420, height: 720 },
  { ...base, page: 'abstract-blue.html', file: '../../../shared/assets/abstract-blue-left.png', width: 422, height: 720 },
  { ...base, page: 'context-diagrams.html', query: 'view=typical', file: 'context-v2-typical.png' },
  { ...base, page: 'context-diagrams.html', query: 'view=colored', file: 'context-v2-colored.png' },
  { ...base, page: 'context-diagrams.html', query: 'view=sliced', file: 'context-v2-sliced.png' },
  { ...base, page: 'context-diagrams.html', query: 'view=webmvc', file: 'context-v2-webmvc.png' }
];
