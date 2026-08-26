# Top 5 Spring Boot Testing Mistakes Developers Trap Into

Your Spring Boot tests are passing, but can you trust them? This session reveals five critical mistakes that silently undermine your test suite's reliability and your confidence in deployments.

We'll tackle the tough questions: How can 100% coverage still miss critical bugs? Why is your test suite so slow? You'll see real tests that look correct but hide serious flaws - transaction rollback confusion, context pollution, and incorrect `@SpringBootTest` usage. We'll also sharpen your test strategy: picking the right test slice, reusing the application context, and slashing build times for faster feedback. Each pitfall comes with practical solutions and alternatives.

Through code examples and live debugging, you'll learn to write tests that actually catch bugs before production, shifting from hope to certainty. Perfect for developers burned by production issues despite "comprehensive" test suites.

Joyful testing awaits!

## The Five Traps

1. The Golden Hammer - `@SpringBootTest` for everything
2. The Context Tax - and the Context Leak
3. The Production Parity Trap - green on H2, red in prod
4. The Phantom Commit - `@Transactional` tests where nothing ever happened
5. The Green Lie - tests that pass but cannot fail

## Slides

The Marp deck and build instructions live in [slides](slides/). Rendered PDFs per venue are committed there as `slides-<venue>-<date>.pdf`.
