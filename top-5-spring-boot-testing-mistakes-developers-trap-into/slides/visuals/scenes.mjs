// Trap-map canvas scenes, exported with: node shared/visuals/export-visuals.mjs top-5-spring-boot-testing-mistakes-developers-trap-into
const base = { page: 'trap-map.html', selector: 'canvas', waitRendered: true };
export const scenes = [
  ...[0, 1, 2, 3, 4, 5].map(n => ({ ...base, query: `view=map&disarmed=${n}`, file: `trap-map-${n}.png`, width: 1920, height: 1080 })),
  ...[1, 2, 3, 4, 5].map(n => ({ ...base, query: `view=zoom&trap=${n}`, file: `trap-zoom-${n}.png`, width: 1920, height: 1080 })),
  { ...base, query: 'view=cover', file: 'cover-green-lie.png', width: 1080, height: 1080 },
  { ...base, query: 'view=traps', file: 'cover-mouse-traps.png', width: 1080, height: 1080 },
  { ...base, query: 'view=agenda', file: 'agenda-mouse-traps.png', width: 1920, height: 1080 },
  ...[1, 2, 3, 4, 5].map(n => ({ ...base, query: `view=trapcard&trap=${n}`, file: `trap-card-${n}.png`, width: 1080, height: 1080 })),
  { ...base, query: 'view=summary', file: 'summary-mouse-traps.png', width: 1920, height: 1080 },
  { ...base, query: 'view=germany', file: 'germany-erlangen.png', width: 400, height: 620 }
];
