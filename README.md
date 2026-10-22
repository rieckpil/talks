# Conference Talks 🎤

**Speaker description (Philip Riecks - Founder PragmaTech Digital Solutions)**: Philip is enabling developers to frequently deliver software with more confidence through actionable tech content and hands-on consulting.

He's an indie hacker, frequent Southeast Asia traveler, squash player, padel newbie, and enjoys jogging in nature.

Links:
- [Blog](https://rieckpil.de)
- [Twitter](https://twitter.com/rieckpil)
- Speaker Image Reference: [400x400](/resources/rieckpil-400x400.jpg) or [200x257](/resources/rieckpil-200x257.jpg)

## Hands-On

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://codespaces.new/rieckpil/talks)

Create a new development environment via [GitHub Codespaces](https://codespaces.new/rieckpil/talksg). Choose a _Region_ close to where you live and select at least a 4-core machine (8-core would be better). GitHub will automatically open the development environment in a Visual Studio Code instance in your web browser.

Don't forget to [shut down the codespace](https://github.com/codespaces) afterward to avoid costs (check your usage [here](https://github.com/settings/billing/summary)).

## Prompt It Right: Spring Boot Testing in the AI Era

**Talk description**: AI now writes most of your test code, and that changes the skill you need. The bottleneck is no longer typing tests by hand; it's knowing what good tests look like, where each kind belongs, and how to prompt AI to produce them. Without that understanding, you get tests that pass but verify nothing.

This session gives you the full picture of testing Spring Boot applications, so you can direct AI with confidence instead of trusting it blindly.

We'll cover the complete testing pyramid: fast unit tests, sliced tests that load only the part of the context you need, and integration tests that exercise the real thing with Testcontainers. For each level, you'll see when to reach for it, what it actually proves, and how to prompt AI to write it well.

Along the way you'll collect practical patterns and recipes you can apply directly to your own codebase, plus the common pitfalls that produce slow, flaky, or misleading tests. We'll close with quick wins that improve almost any Spring Boot test suite.

Whether you're newer to Spring Boot testing or want to sharpen how you collaborate with AI tools, you'll leave knowing not just how to write tests, but how to judge whether the ones AI hands you are worth keeping.

- [Source Code](prompt-it-right/)
- Slides: [Devoxx Belgium 2026](prompt-it-right/slides/slides-devoxx-be-2026-10-05.pdf)
- Recordings: none

## Ship Fast, Sleep Well: Fearless Spring Boot Deployments in the AI Era

**Talk description**: "Never deploy on Friday" is one of the most popular rules in software development. But if a deployment is too risky for Friday, it's too risky for Tuesday, too.

Fear of deploying is a symptom, not a law of nature. It shows up when changes are large, feedback is slow, and nobody really trusts the test suite. AI-assisted coding makes this worse: we now produce more code faster than ever, while our confidence in it often stays the same. Crossing your fingers doesn't scale.

In this talk, we'll look at what it takes to make deployments boring at any time of day. We'll start with why deploying more often actually reduces risk, then focus on the foundation that makes it possible: a fast, reliable, and comprehensive test suite.

You'll see how to structure tests so they catch real bugs in minutes, how to eliminate flaky tests, and how to measure whether your tests deserve your trust. We'll finish with the safety nets every production deployment needs: feature flags, backward-compatible database migrations, progressive rollouts, and automated rollbacks.

Examples use Java and Spring Boot, but the principles apply to any technology stack. You'll leave with a concrete checklist to bring your team one step closer to a stress-free Friday deploy.

- [Source Code](ship-fast-sleep-well/)
- Slides: [DATEV Coding Festival 2026](ship-fast-sleep-well/slides/DATEV-Coding-Festival-2026.pdf)
- Recordings: none

## The Need for Speed: Cut Your Spring Boot Build Times by 70%

**Talk description**: Your Spring Boot test suite is a time thief. Every context reload, every unnecessary restart, every `@DirtiesContext` annotation is stealing precious minutes from your development flow. But what if those 20-minute test runs could shrink to just 6 minutes?

This session reveals the game-changing power of Spring's TestContext caching - a feature hiding in plain sight. We'll dissect how Spring decides to reuse or rebuild application contexts, expose the silent performance killers in your test suite, and demonstrate proven strategies that deliver dramatic speed improvements.

Through hands-on examples, you'll discover why `@MockBean` might be your worst enemy, how test execution order affects performance, and which configuration patterns maximize context reuse. We'll benchmark real applications, showing exactly where those 70% gains come from.

Stop accepting slow tests as "just the way it is." Leave with a concrete action plan to accelerate your test suite today - no new frameworks, no rewrites, just smarter testing.

- [Source Code](the-need-for-speed/)
- Slides: [JavaCro 2026](the-need-for-speed/slides/need-for-speed-javacro-2026.pdf)
- Recordings: none

## Top 5 Spring Boot Testing Mistakes Developers Trap Into

**Talk description**: Your Spring Boot tests are passing, but can you trust them? In recent years, most of your tests aren't written by you - an AI wrote them while you reviewed the diff. That makes testing more important than ever, yet for many teams it remains a neglected afterthought.

This session reveals five mistakes that silently undermine your test suite's reliability and your confidence in deployments. We'll tackle the tough questions: How can 100% coverage still miss critical bugs? Why is your test suite so slow? Why does everything pass on H2 and fail in production?

You'll see real tests that look correct but hide serious flaws: `@SpringBootTest` as the golden hammer, the hidden context tax of careless `@MockBean` usage, in-memory databases that fake production parity, `@Transactional` rollbacks that make commits vanish, and green tests that can never fail. Each pitfall comes with practical fixes: picking the right test slice, reusing the application context, testing against real infrastructure with Testcontainers, and challenging your coverage with mutation testing.

You'll learn to judge tests, whether written by a human or an AI, and ship with certainty instead of hope. Perfect for developers burned by production issues despite "comprehensive" test suites.

Joyful testing awaits!

- [Source Code](top-5-spring-boot-testing-mistakes-developers-trap-into/)
- Slides: [JavaCro 2026](top-5-spring-boot-testing-mistakes-developers-trap-into/slides/slides-javacro-2026-10-13.pdf), [Bozen 2026](top-5-spring-boot-testing-mistakes-developers-trap-into/slides/slides-bozen-2026-09-08.pdf)
- Recordings: none

## Testing Spring Boot Applications Demystified

<p align="center">
  <a href="https://rieckpil.de">
    <img src="/resources/demystify.jpg" alt="Demystify"/>
  </a>
</p>

**Talk description**: Testing plays a crucial role in software development, allowing you to catch bugs, ensure your code functions as intended, and provide confidence in the reliability and stability of your applications.

However, testing Spring Boot applications can sometimes feel like navigating a labyrinth of challenges – from managing dependencies and external systems to crafting effective tests that reflect the behavior of your code.

Whether you’re a seasoned developer or just starting your journey with Spring Boot, this workshop is designed to unravel the complexities and pitfalls of testing Spring Boot applications and empower you to become more productive and confident in your testing efforts.

In this workshop, we will demystify testing Spring Boot applications by providing you with clear explanations, practical insights, and actionable best practices. Philip will guide you through the common pitfalls faced by developers, share time-tested recipes for success, and equip you with the tools and knowledge you need to write comprehensive and effective tests.

- [Source Code](testing-spring-boot-applications-demystified/)
- Slides: [Java Guild@Elia 2026](testing-spring-boot-applications-demystified/slides/slides-java-guild-elia-2026-10-20.pdf), JUG Munich 205, JUG Zürich 2025, JUG St. Gallen 2025, JUG Bern 2025, [Java User Group Hamburg 2025](testing-spring-boot-applications-demystified/slides/slides-jug-hamburg-2025.pdf), [Java User Group Barcelona 2025](testing-spring-boot-applications-demystified/slides/slides-jug-barcelona-2025.pdf), [Spring I/O 2024](https://pitch.com/v/spring-io-2024-testing-spring-boot-applications-demystified-vy8zf9), 
- Recordings: [JUG Munich 2025](https://youtu.be/WW3tAcaiSd0?si=ou8olvUuhP-MLneL)

## Stop Fighting Your Spring Boot Tests


<p align="center">
  <a href="https://rieckpil.de">
    <img src="/resources/stop-fighting-your-spring-boot-tests.jpg" alt="Stop Fighting Your Spring Boot Tests"/>
  </a>
</p>


**Talk Description**: Is Your Spring Boot Team Waiting 20+ Minutes for Tests to Run? Discover how your Spring Boot team can cut build
times, eliminate flaky tests, and ship with confidence.

Stop wasting hours waiting for slow tests. Stop skipping tests because they take too long. Stop fearing refactoring because your test suite might explode.

This 45-60 minute session reveals exactly why your Spring Boot tests fight back and gives you practical strategies to fix it - no fluff, just actionable insights you can implement this week.


- Source Code
- Slides:
- Recordings: none

## How Fixing a Broken Window Cut Down Our Build Time by 50%

<p align="center">
  <a href="https://rieckpil.de">
    <img src="/resources/broken-window.png" alt="Broken Window"/>
  </a>
</p>

**Talk description**: The broken windows (not the operating system - 😁) theory not only applies to criminological cases but also codebases. It only takes one lazy developer to break the first window. Chances are high that others will follow a similar path and take workarounds or re-apply a not so optimal pattern thinking, "It passed the code review, I must not be that bad".

For our Spring Boot integration test setup, we had such a broken window that was causing ever-growing build times. While most books about clean architecture emphasize composition over inheritance, we drifted off from this rule and coupled our tests to an AbstractIntegrationTest class that we then annotated with `@DirtiesContext`...

This resulted in ever-growing build times as each new integration test was launching its own Spring TestContext, adding valuable seconds to the overall build time. Our feedback cycles, as well as the overall satisfaction of the team, started to suffer from this.

This talk describes our journey of getting rid of `@DirtiesContext` on top of an abstract test class (aka. the "broken window") that made reusing the Spring TestContext impossible and resulted in ever-growing build times. Furthermore, we'll discuss recipes, best practices, and antipatterns for resuing the Spring TestContext and testing Spring Boot applications in general.

After fixing this "broken window", our overall build time went down by 50% from 25 to 12 minutes.

- [Source Code](/fixing-a-broken-window/)
- [Slides](https://speakerdeck.com/rieckpil/how-fixing-a-broken-window-cut-down-our-build-time-by-50-percent)
- Recordings: [Spring I/O 2022](https://www.youtube.com/watch?v=c-GV2PxymoY)

## Things I Wish I Knew When I Started Testing Spring Boot Applications

<p align="center">
  <a href="https://rieckpil.de">
    <img src="/resources/getting-started.jpg" alt="Getting Started"/>
  </a>
</p>

**Talk description**: Getting started with Spring Boot and its auto-configuration mechanism can be a hurdle for new developers.

Once you get your first Spring Boot application up- and running, writing tests for it is the last thing you care about. You’re happy that your code does its job.

However, as soon as you try to integrate your changes, you face a pull request rejection because your lead developer reminds you that tests are missing.

Testing is an integral part of software development, and unfortunately, some teams treat this topic neglectfully. That’s bad for the future maintenance and overall health of their project. Fortunately, both Spring Test and Spring Boot offer excellent support for testing your application.

This talk will give you an overview of best practices, pitfalls, and recipes for testing Spring Boot applications. Simply put, with this talk, I’ll share the things that I wish I had known when I started testing Spring Boot applications.


- [Source Code](/things-i-wish-i-knew-spring-boot-testing/)
- [Slides](https://speakerdeck.com/rieckpil/things-i-wish-i-knew-when-i-started-testing-spring-boot-applications)
- Recordings: [Spring I/O 2023](https://www.youtube.com/watch?v=hR0bbk2tsF0) & [Devoxx Belgium 2022](https://www.youtube.com/watch?v=5Td7vAS9qJI)
