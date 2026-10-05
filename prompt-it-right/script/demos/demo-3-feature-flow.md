# Demo 3: Build a feature with the router skill (8 min)

Where: backup only (not on the slides anymore).

## Prompt
> A pet cannot have two visits on the same day. Implement it. Use plan mode first.

## What to point out
1. Plan mode: the agent lists what changes before it edits
2. Router skill loads, picks the cheapest tests: unit test `PetTest` for the rule, web slice test for the error response
3. New test classes in the skill style (one assertion chain, `.as(...)` messages, no `@BeforeEach`)
4. Translations added for every language file (nice detail)
5. Agent runs new tests, then the fast phase, then the container phase
6. Final report: plan table plus "not tested" list. Ask the audience: "Would you accept this PR?"

## Fallback
Recording in `fallbacks.md`. If the agent goes off track, say so out loud and show the review step. That is the lesson.
