# Run sheet

Rule: if a block runs more than 3 min over, use the cut list in `../TALK-PLAN.md`. Never skip a FAQ, shorten it.

| Clock (Mon) | Elapsed min | Block | Key message | Slide section |
|---|---|---|---|---|
| 09:30 | 0 | Opening | You will leave with a plan, not a pitch. Beginner level on purpose | Opening |
| 09:35 | 5 | **Block 1** Crash course (60 min) | Pick the cheapest test that proves the behavior. Ends with the five properties of a good suite | Part 1 |
| 10:35 | 65 | **Block 1 FAQ** (10 min) | Answer questions from the room | FAQ 1 |
| 10:45 | 75 | BREAK (30 min) | Back at 11:15 sharp | Break |
| 11:15 | 105 | **Block 2** Rationale (15 min) | The green check must carry the weight | Part 2 |
| 11:30 | 120 | **Block 2** Skills (20 min) | Teach the agent how YOU test | Part 3a |
| 11:50 | 140 | **Block 2** Fast feedback (12 min) | Fast feedback is a feature for agents | Part 3b |
| 12:02 | 152 | **Block 2** Tooling (13 min) | Small steps, good tools, guardrails | Part 3c |
| 12:15 | 165 | **Block 2** Wrap-up (5 min) | Evidence, five takeaways, one soft pointer | Wrap-up |
| 12:20 | 170 | **Block 2 FAQ** (10 min) | Answer questions from the room | FAQ 2 |
| 12:30 | 180 | End | | Closing |

## Before you go on stage

- Mentimeter open for the polls, Devoxx app talk page open in a tab (comments for the FAQ rounds), slide deck in presenter mode (HTML)
- Terminal and IDE font large, Docker running
- Demo project reset: `./demo/setup.sh`
- Fallback videos in one folder

## FAQ rounds

- Say it early (slide "Three ways to ask"): interrupt me live, wait for the FAQ, or save the question in the Devoxx app comments (QR).
- At the FAQ slide: sort by votes, answer 4-6 questions. 90 seconds per answer.
- Questions that need a demo or long answer: promise a follow-up in the hallway or newsletter.
- Prepared backup questions: `faq-block-1.md`, `faq-block-2.md`.

## During the break (30 min)

- Reset demo project, close Part 1 tabs
- Start Docker, pre-pull images
- Check microphone and clicker battery
- Read the Devoxx app comments from block 1, keep the unanswered ones for FAQ 2
