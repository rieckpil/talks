import React, { useEffect, useState } from 'react';
import {
  AbsoluteFill,
  interpolate,
  spring,
  useCurrentFrame,
  useVideoConfig,
  delayRender,
  continueRender,
} from 'remotion';

export const FPS = 15;
export const WIDTH = 1280;
export const HEIGHT = 680;

/* Beat boundaries, in frames. Each act runs long enough to be read aloud. */
const ACT = {
  setup: 0,
  orderStart: 18,      // OrderIT asks the cache
  orderMiss: 34,       // ... and misses
  orderBuilt: 54,      // ... so a context gets built and stored
  paymentStart: 96,    // PaymentIT asks with the same key
  paymentHit: 112,     // ... and hits
  checkoutStart: 156,  // CheckoutIT asks with a different key
  checkoutMiss: 172,
  checkoutBuilt: 190,
  summary: 232,
};
export const TOTAL_FRAMES = 285;

/* Palette mirrors pragmatech.css section.light. */
const INK = '#0f172a';
const MUTED = '#64748b';
const ACCENT = '#0284c7';
const GREEN = '#00a34a';
const RED = '#dc2626';
const AMBER = '#c98a00';
const SURFACE = '#f1f5f9';
const BORDER = '#e2e8f0';

const FONT_URL =
  'https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&family=Space+Grotesk:wght@500;600;700&family=JetBrains+Mono:wght@400;700&display=swap';

/** Blocks rendering until the webfonts are ready, so no frame ships with a fallback face. */
const useWebFonts = () => {
  const [handle] = useState(() => delayRender('loading fonts'));
  useEffect(() => {
    const link = document.createElement('link');
    link.rel = 'stylesheet';
    link.href = FONT_URL;
    document.head.appendChild(link);
    document.fonts.ready.then(() => continueRender(handle));
  }, [handle]);
};

/** 0 -> 1 ease-in-out over [from, from + length). */
const ramp = (frame: number, from: number, length: number) =>
  interpolate(frame, [from, from + length], [0, 1], {
    extrapolateLeft: 'clamp',
    extrapolateRight: 'clamp',
  });

type TestCardProps = {
  name: string;
  hash: string;
  hashColor: string;
  top: number;
  active: boolean;
  appearAt: number;
  runtime?: { text: string; color: string; at: number };
};

const TestCard: React.FC<TestCardProps> = ({ name, hash, hashColor, top, active, appearAt, runtime }) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const enter = spring({ frame: frame - appearAt, fps, config: { damping: 200 } });

  return (
    <div
      style={{
        position: 'absolute',
        left: 40,
        top,
        width: 330,
        opacity: enter,
        transform: `translateY(${(1 - enter) * 16}px)`,
      }}
    >
      <div
        style={{
          background: active ? '#ffffff' : SURFACE,
          border: `1px solid ${active ? hashColor : BORDER}`,
          borderLeft: `7px solid ${active ? hashColor : BORDER}`,
          borderRadius: 14,
          padding: '14px 18px',
          boxShadow: active ? `0 0 0 3px ${hashColor}22` : 'none',
          transition: 'none',
        }}
      >
        <div style={{ fontFamily: 'Space Grotesk, sans-serif', fontSize: 27, fontWeight: 700, color: INK }}>
          {name}
        </div>
        <div style={{ fontSize: 14, color: MUTED, marginTop: 6, letterSpacing: '0.02em' }}>
          unique context configuration
        </div>
        <div style={{ fontFamily: 'JetBrains Mono, monospace', fontSize: 19, color: hashColor, marginTop: 1 }}>
          {hash}
        </div>
        <div
          style={{
            marginTop: 12,
            paddingTop: 10,
            borderTop: `1px solid ${BORDER}`,
            display: 'flex',
            alignItems: 'baseline',
            justifyContent: 'space-between',
            gap: 10,
            opacity: runtime && frame >= runtime.at ? ramp(frame, runtime.at, 6) : 0,
          }}
        >
          <span style={{ fontSize: 14, color: MUTED }}>Test Execution Time</span>
          <span
            style={{
              fontFamily: 'Space Grotesk, sans-serif',
              fontSize: 24,
              fontWeight: 700,
              color: runtime ? runtime.color : MUTED,
            }}
          >
            {runtime ? runtime.text : ''}
          </span>
        </div>
      </div>
    </div>
  );
};

/** A stored ApplicationContext: a ring of beans that pop in one after another. */
const ContextBlob: React.FC<{ cx: number; cy: number; r: number; builtAt: number; tint: string }> = ({
  cx,
  cy,
  r,
  builtAt,
  tint,
}) => {
  const frame = useCurrentFrame();
  const { fps } = useVideoConfig();
  const beans = 16;

  const shell = spring({ frame: frame - builtAt, fps, config: { damping: 200 } });
  if (frame < builtAt) return null;

  return (
    <g>
      <circle cx={cx} cy={cy} r={r * shell} fill="#ffffff" stroke={tint} strokeWidth={2.5} />
      {Array.from({ length: beans }).map((_, index) => {
        const beanAt = builtAt + 2 + index * 1.1;
        const pop = spring({ frame: frame - beanAt, fps, config: { damping: 14, stiffness: 180 } });
        const angle = (index / beans) * Math.PI * 2 - Math.PI / 2;
        const radius = r * (index % 2 === 0 ? 0.62 : 0.33);
        return (
          <circle
            key={index}
            cx={cx + Math.cos(angle) * radius}
            cy={cy + Math.sin(angle) * radius}
            r={9 * pop}
            fill={index % 3 === 0 ? tint : index % 3 === 1 ? '#ffffff' : SURFACE}
            stroke={tint}
            strokeWidth={1.5}
          />
        );
      })}
    </g>
  );
};

/** Request arrow travelling from a test card to the cache, then the answer coming back. */
const Wire: React.FC<{
  y: number;
  color: string;
  startAt: number;
  label: string;
  labelAt: number;
  returning?: boolean;
}> = ({ y, color, startAt, label, labelAt, returning }) => {
  const frame = useCurrentFrame();
  const x0 = 380;
  const x1 = 690;
  const progress = ramp(frame, startAt, 10);
  if (frame < startAt) return null;

  const head = returning ? x1 - (x1 - x0) * progress : x0 + (x1 - x0) * progress;

  return (
    <g>
      <line
        x1={returning ? x1 : x0}
        y1={y}
        x2={head}
        y2={y}
        stroke={color}
        strokeWidth={3}
        strokeLinecap="round"
      />
      <polygon
        points={
          returning
            ? `${head},${y} ${head + 12},${y - 6} ${head + 12},${y + 6}`
            : `${head},${y} ${head - 12},${y - 6} ${head - 12},${y + 6}`
        }
        fill={color}
        opacity={progress}
      />
      {frame >= labelAt && (
        <text
          x={(x0 + x1) / 2}
          y={y - 12}
          textAnchor="middle"
          fill={color}
          style={{ fontFamily: 'Space Grotesk, sans-serif', fontSize: 21, fontWeight: 700 }}
          opacity={ramp(frame, labelAt, 5)}
        >
          {label}
        </text>
      )}
    </g>
  );
};

export const ContextCache: React.FC = () => {
  useWebFonts();
  const frame = useCurrentFrame();

  const firstKeyIn = ramp(frame, ACT.orderBuilt, 8);
  const secondKeyIn = ramp(frame, ACT.checkoutBuilt, 8);
  const summaryIn = ramp(frame, ACT.summary, 10);

  return (
    <AbsoluteFill style={{ background: '#ffffff', fontFamily: 'Inter, sans-serif' }}>
      {/* Left column: the three test classes, in the order they run */}
      <TestCard
        name="OrderIT"
        hash="#327321289"
        hashColor={ACCENT}
        top={70}
        appearAt={-15}
        active={frame >= ACT.orderStart && frame < ACT.paymentStart}
        runtime={{ text: '3506 ms', color: RED, at: ACT.orderBuilt + 8 }}
      />
      <TestCard
        name="PaymentIT"
        hash="#327321289"
        hashColor={ACCENT}
        top={250}
        appearAt={-14}
        active={frame >= ACT.paymentStart && frame < ACT.checkoutStart}
        runtime={{ text: '406 ms', color: GREEN, at: ACT.paymentHit + 12 }}
      />
      <TestCard
        name="CheckoutIT"
        hash="#565212314"
        hashColor={AMBER}
        top={430}
        appearAt={-13}
        active={frame >= ACT.checkoutStart}
        runtime={{ text: '4506 ms', color: RED, at: ACT.checkoutBuilt + 8 }}
      />

      <svg width={WIDTH} height={HEIGHT} style={{ position: 'absolute', inset: 0 }}>
        {/* The cache itself */}
        <rect x={700} y={70} width={540} height={520} rx={18} fill="#ffffff" stroke={INK} strokeWidth={2} />
        <text
          x={970}
          y={106}
          textAnchor="middle"
          fill={INK}
          style={{ fontFamily: 'Space Grotesk, sans-serif', fontSize: 25, fontWeight: 700 }}
        >
          Spring TestContext Cache
        </text>
        <line x1={700} y1={122} x2={1240} y2={122} stroke={BORDER} strokeWidth={2} />
        <text x={775} y={150} fill={MUTED} style={{ fontFamily: 'JetBrains Mono, monospace', fontSize: 16 }}>
          CACHE KEY
        </text>
        <text x={1030} y={150} fill={MUTED} style={{ fontFamily: 'JetBrains Mono, monospace', fontSize: 16 }}>
          CONTEXT
        </text>

        {/* First entry */}
        <text x={730} y={232} fill={MUTED} opacity={firstKeyIn} style={{ fontSize: 14 }}>
          unique context configuration
        </text>
        <text
          x={730}
          y={256}
          fill={ACCENT}
          opacity={firstKeyIn}
          style={{ fontFamily: 'JetBrains Mono, monospace', fontSize: 20, fontWeight: 700 }}
        >
          #327321289
        </text>
        <ContextBlob cx={1090} cy={243} r={78} builtAt={ACT.orderBuilt} tint={ACCENT} />

        {/* Second entry, only once CheckoutIT forces it */}
        <text x={730} y={412} fill={MUTED} opacity={secondKeyIn} style={{ fontSize: 14 }}>
          unique context configuration
        </text>
        <text
          x={730}
          y={436}
          fill={AMBER}
          opacity={secondKeyIn}
          style={{ fontFamily: 'JetBrains Mono, monospace', fontSize: 20, fontWeight: 700 }}
        >
          #565212314
        </text>
        <ContextBlob cx={1090} cy={423} r={78} builtAt={ACT.checkoutBuilt} tint={AMBER} />

        {/* Each wire stays on screen once drawn, so by the end the whole story is
            visible at once: two misses that paid, one hit that did not.
            <Wire> renders nothing before its own startAt. */}
        <Wire y={143} color={RED} startAt={ACT.orderStart} label="MISS - build it" labelAt={ACT.orderMiss} />
        <Wire y={313} color={GREEN} startAt={ACT.paymentStart} label="HIT - reuse it" labelAt={ACT.paymentHit} />
        <Wire y={338} color={GREEN} startAt={ACT.paymentHit} label="" labelAt={99999} returning />
        <Wire y={503} color={AMBER} startAt={ACT.checkoutStart} label="MISS - build another" labelAt={ACT.checkoutMiss} />
      </svg>

      {/* Closing tally: the numbers first, then what they cost */}
      <div
        style={{
          position: 'absolute',
          left: 0,
          right: 0,
          bottom: 14,
          textAlign: 'center',
          opacity: summaryIn,
          transform: `translateY(${(1 - summaryIn) * 10}px)`,
        }}
      >
        <div
          style={{
            fontFamily: 'Space Grotesk, sans-serif',
            fontSize: 24,
            fontWeight: 700,
            color: INK,
          }}
        >
          Result: 3 tests ran, but 2 contexts had to start
        </div>
        <div style={{ fontSize: 19, color: MUTED, marginTop: 5 }}>
          Only <span style={{ color: GREEN, fontWeight: 700 }}>PaymentIT</span> reused a cached context
          and finished fast - the other two each paid for a fresh one.
        </div>
      </div>
    </AbsoluteFill>
  );
};
