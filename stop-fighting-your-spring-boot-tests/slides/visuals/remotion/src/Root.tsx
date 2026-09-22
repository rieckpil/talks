import { Composition } from 'remotion';
import { ContextCache, TOTAL_FRAMES, FPS, WIDTH, HEIGHT } from './ContextCache';

export const RemotionRoot: React.FC = () => (
  <Composition
    id="ContextCache"
    component={ContextCache}
    durationInFrames={TOTAL_FRAMES}
    fps={FPS}
    width={WIDTH}
    height={HEIGHT}
  />
);
