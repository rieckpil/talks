import { Composition } from 'remotion';
import { ContextCache as ContextCacheSpeed, TOTAL_FRAMES as SPEED_FRAMES } from './the-need-for-speed/ContextCache';
import { ContextCache, TOTAL_FRAMES, FPS, WIDTH, HEIGHT } from './stop-fighting/ContextCache';

export const RemotionRoot: React.FC = () => (
  <>
  <Composition
    id="ContextCache"
    component={ContextCache}
    durationInFrames={TOTAL_FRAMES}
    fps={FPS}
    width={WIDTH}
    height={HEIGHT}
  />
  <Composition
    id="ContextCacheSpeed"
    component={ContextCacheSpeed}
    durationInFrames={SPEED_FRAMES}
    fps={FPS}
    width={WIDTH}
    height={HEIGHT}
  />
  </>
);
