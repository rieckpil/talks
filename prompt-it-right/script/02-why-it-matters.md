# Part 2: Why a fast, complete suite matters (15 min, 11:15 - 11:30, first part of block 2)

Goal: change the mindset. Open block 2 with the six-box recap slide (1 min, keywords only: toolbox, test types, Testcontainers, context caching, parallelization, mutation testing) and a recall of the five properties from block 1. Verification is the new constraint, not code generation.

| Min | Beat | Content |
|---|---|---|
| 2 | The past | Testing as an afterthought. Poll result from Mentimeter: "How much of your code is AI-written today?" |
| 3 | The F1 engine | Generation is cheap, review is not. 2,000 lines per day written vs 200 lines per hour read. Pipeline flow: prompt, diff, review, merge (review highlighted) |
| 3 | Judgment is the job | Someone still explains the outage. The conductor slide. "Green check must carry the weight" |
| 4 | Link back to the five properties | Which property breaks first with an agent? Fast: the loop. Deterministic: retries. Isolated: parallel agents, worktrees. Locating message: the agent reads the failure |
| 3 | What agents do with a bad suite | Slow suite: agent skips or runs one test. Flaky suite: agent retries or deletes. Weak assertions: agent "fixes" the test. Mock-everything: green but wrong. Add real examples |

Agent loop slide idea: edit - run tests - read failure - edit. Every second in the loop is paid per iteration. A 10 min suite makes the loop useless, a 60 s fast phase keeps it alive.

Transition: "So how do I teach the agent to write the tests I would write? With skills."

Reuse: course repo `slides/m2-verification-constraint/02-formula-1-engine.deck.md`, `03-trustworthy-suite.deck.md`. Rewrite in talk voice, drop course branding.

TODO: collect 2-3 own anecdotes of agents gaming tests.
