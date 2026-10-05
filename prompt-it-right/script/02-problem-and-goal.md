# Block 2, part 1: The problem today and the goal (10 min, 11:15 - 11:25)

Open with the six-box recap of block 1 (1 min, keywords only), then: now we put an AI agent on top of this fundament.

| Min | Beat | Content |
|---|---|---|
| 1 | Recap + divider | Six boxes of block 1. Divider "Code is generated. Trust is not." |
| 1 | The problem | Most of our code is generated. Testing was often an afterthought in the past. AI will not fix this by itself |
| 2 | Formula 1 engine | Statement, then graphic 1: fast car, bumpy road, no brakes, wall (production). Source: `slides/visuals/f1-engine.html` |
| 1 | Verification is the constraint | AI generates code in seconds, human review capacity is limited, so we need a safety net: the test suite |
| 1 | Brakes, road, driver | Graphic 2: brakes = fast comprehensive tests, road = fast feedback in CI, driver = you + skills |
| 2 | DORA (quick) | Core model: fast feedback, fast flow, climate for learning predict delivery performance, which predicts organizational performance and well-being. Source: pragmatech.digital `data/homepage.yaml`, image `dora-core-summary.png` |
| 2 | The goal | A fast and comprehensive test suite. Both at once |

Transition: "How do I get the agent to write such tests? Let's start with a prompt."
