# Block 2, part 1: The goal - a fast and comprehensive test suite (10 min, 11:15 - 11:25)

Open with the six-box recap slide (1 min, keywords only), then: now we put an AI agent on top of this fundament.

| Min | Beat | Content |
|---|---|---|
| 1 | Recap + goal slide | Six boxes of block 1. Section divider "A fast and comprehensive test suite" |
| 1 | Bottleneck moved | Writing code is cheap, verifying is not. Throughput is not the constraint, human attention is |
| 2 | Formula 1 engine | Graphic 1: fast car, bumpy road, no brakes, wall (production). Graphic 2: brakes = fast comprehensive tests, road = fast feedback in CI, driver = you + skills. Visual source: `slides/visuals/f1-engine.html` |
| 1 | Fast feedback engine | Image from pragmatech.digital: fast feedback loops (CI and test automation) turn AI output into shipped value |
| 2 | DORA | Core model: fast feedback, fast flow, climate for learning predict delivery performance, which predicts organizational performance and well-being |
| 2 | DORA metrics reasoning | Table: deployment frequency, lead time, change failure rate, time to restore. Say it is my reasoning |
| 1 | The goal | Fast AND comprehensive. Confidence in every commit |

Source for DORA reasoning: `~/Development/git/pragmatech.digital/data/homepage.yaml` (fastFeedback section). Images: `slides/assets/fast-feedback-foundation.png`, `dora-core-summary.png`.

Transition: "How do I make the agent produce tests that are fast and comprehensive, every time? With a skill library."
