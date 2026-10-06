---
marp: true
theme: pragmatech
title: 'Ship Fast, Sleep Well: Fearless Spring Boot Deployments in the AI Era'
class: light
paginate: true
transition: pt-fade
header: 'Ship Fast, Sleep Well: Fearless Spring Boot Deployments in the AI Era · DATEV Coding Festival 2k26'
footer: '![](assets/logo.webp) Philip Riecks · [@rieckpil](https://x.com/rieckpil) · [PragmaTech GmbH](https://pragmatech.digital/)'
style: |
  section[class*="cards"] > ul { display: grid; gap: 20px; list-style: none; padding: 0; margin: 0.5em 0 0; }
  section.cards2 > ul { grid-template-columns: repeat(2, 1fr); }
  section.cards3 > ul { grid-template-columns: repeat(3, 1fr); }
  section.cards4 > ul { grid-template-columns: repeat(4, 1fr); }
  section[class*="cards"] > ul > li { box-sizing: border-box; display: flex; flex-direction: column; justify-content: center; align-items: center; text-align: center; height: 175px; margin: 0; padding: 0.5em 0.4em; border: 3px solid var(--pt-heading); background: var(--pt-bg-1); border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px; box-shadow: 3px 4px 0 rgba(15, 23, 42, 0.12); font-family: 'Architects Daughter', cursive; font-size: 1.2em; line-height: 1.15; }
  section[class*="cards"].big > ul > li { height: 255px; font-size: 1.1em; }
  section[class*="cards"].dark > ul > li { border-color: #b91c1c; }
  section[class*="cards"] > ul > li strong { font-size: 1.2em; color: var(--pt-link); }
  section[class*="cards"].dark > ul > li strong { color: #b91c1c; }
  section[class*="cards"] > ul > li small { display: block; margin-top: 0.4em; font-size: 0.7em; color: var(--pt-heading); }
  .phases { display: grid; grid-template-columns: repeat(4, 1fr); gap: 18px; margin-top: 0.4em; font-family: 'Architects Daughter', cursive; }
  .phase { box-sizing: border-box; padding: 0.6em 0.8em; border: 3px solid var(--pt-link); background: var(--pt-bg-1); border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px; box-shadow: 3px 4px 0 rgba(15, 23, 42, 0.12); font-size: 1.05em; line-height: 1.25; min-height: 330px; }
  .phase h3 { margin: 0 0 0.4em; color: var(--pt-link); font-size: 1.35em; text-align: center; }
  .phase ul { list-style: none; padding: 0; margin: 0; }
  .phase li { margin: 0.35em 0; font-size: 0.85em; }
  .phases.two { grid-template-columns: repeat(2, 1fr); } .phases.two .phase { min-height: 300px; font-size: 1.2em; }
  .phase.hot { background: var(--pt-bg-1); box-shadow: 0 0 0 4px rgba(14, 165, 233, 0.25), 3px 4px 0 rgba(15, 23, 42, 0.12); }
  .flow.xl { gap: 22px; } .flow.xl .sketch { font-size: 1.15em; padding: 0.7em 1em; } .flow.xl .arrow { font-size: 2.2em; }
  .flow.nw { flex-wrap: nowrap; } .flow.nw .sketch { font-size: 0.95em; padding: 0.5em 0.6em; } .flow.nw .arrow { font-size: 1.6em; }
  .flow.nw.big .sketch { font-size: 1.2em; padding: 0.8em 1em; }
  .flow.bad .sketch { border-color: #b91c1c; color: #b91c1c; }
  .ribbon { margin-top: 0.8em; padding: 0.5em 1em; text-align: center; font-family: 'Architects Daughter', cursive; font-size: 1.2em; border: 3px dashed var(--pt-link); border-radius: 255px 15px 225px 15px / 15px 225px 15px 255px; color: var(--pt-link); }
  .spans { font-family: 'Architects Daughter', cursive; margin-top: 0.5em; }
  .spans .row { display: flex; align-items: center; gap: 12px; margin: 10px 0; font-size: 1.05em; }
  .spans .label { width: 300px; white-space: nowrap; text-align: right; }
  .spans .bar { box-sizing: border-box; height: 38px; border: 2.5px solid var(--pt-heading); background: var(--pt-bg-1); border-radius: 18px 8px 22px 8px / 8px 20px 8px 18px; padding: 0 0.6em; display: flex; align-items: center; font-size: 0.8em; color: var(--pt-muted); }
  .spans .bar.slow { border-color: #b91c1c; color: #b91c1c; background: #fef2f2; }
  .spans .track { flex: 1; }

---

<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->

![bg fit](assets/datev-coding-festival.png)

---

<!-- _class: light title -->
<!-- _paginate: false -->
<!-- _header: '' -->

![bg left:33%](assets/abstract-blue-left.png)

# Ship Fast, **Sleep Well**

## Fearless Spring Boot Deployments in the AI Era

DATEV Coding Festival 2026 · October 6, 2026

<!--
Notes:
- TODO: add duration and room.
-->

---

<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->

<!--
Notes:
- Image prompt: visuals/image-prompts.md (horror picture). Replace assets/horror-friday.png.
- Tell the story: it is Friday, 16:00, the pager rings, a critical bug in production.
-->

![bg fit](assets/horror-friday.png)

---

<!-- _class: light reveal cards3 dark -->

<!--
Notes:
- One box per click (HTML deck only). Ask the room: who knows at least three of these?
-->

## Friday, 4 PM. You are on call.

* **Vibe-coded hotfix**<small>the AI says: looks good</small>
* **45 min pipeline**<small>for a one-line change</small>
* **Tests nobody trusts**<small>what do they verify?</small>
* **No feature flag**<small>rollback or nothing</small>
* **Logs without context**<small>which user? which request?</small>
* **No runbook**<small>who knows what to do?</small>

---

<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->

<!--
Notes:
- Image prompt: visuals/image-prompts.md (target picture). Replace assets/target-state.png.
-->

![bg fit](assets/target-state.png)

---

<!-- _class: light reveal cards3 -->

<!--
Notes:
- The same Friday, a different team.
-->

## Same Friday. Confidence in every commit.

* **Fast pipeline**<small>feedback in minutes</small>
* **Meaningful tests**<small>a real safety net</small>
* **Flag it off**<small>faster than a rollback</small>
* **Structured logs and traces**<small>find the problem fast</small>
* **Alerts and runbooks**<small>calm, known steps</small>
* **Canary tests**<small>we know before users do</small>

---

<!--
Notes:
- Source: DORA Core Model, as summarized on pragmatech.digital. Fast feedback is a capability that predicts delivery performance.
-->

## Backed by the DORA Research

![h:20%](assets/dora-core-summary.png)

DORA's core model puts **fast feedback** next to fast flow and a climate for learning.

- These capabilities predict **software delivery performance**
- Delivery performance predicts **organizational performance** and well-being

---

<!-- _class: light -->

<!--
Notes:
- This is the goal of the talk: how a team moves from A to B, which role AI plays and which processes we need.
-->

## The Goal: From A to B

<div class="flow nw xl">
  <div class="sketch" style="border-color:#b91c1c;color:#b91c1c">A: Hope and heroics<small>slow, scary, on call</small></div>
  <div class="arrow">→</div>
  <div class="sketch accent">Processes<small>build · verify · ship · operate</small></div>
  <div class="arrow">+</div>
  <div class="sketch accent alt">AI<small>tooling and skills</small></div>
  <div class="arrow">→</div>
  <div class="sketch">B: Confident shipping<small>fast, calm, repeatable</small></div>
</div>

<div class="ribbon">AI makes us faster. Processes make us safe.</div>

---

![bg right:33%](assets/herzogenaurach.jpg)

### About Philip

- Software Engineer from Herzogenaurach (HQ of adidas & Puma), right next to Nuremberg 🍻
- Focus on **testing** Java and Spring Boot applications, blogging and content creation 🍃
- Founder of [PragmaTech GmbH](https://pragmatech.digital/) - **Enabling Developers to Frequently Deliver** Software with **More Confidence**

- Here before: **DATEV Coding Festival 2025** with a full-day workshop on Spring Boot testing, and two weeks ago the **DATEV Software Craft Community**, also on Spring Boot testing

<!--
Notes:
- Herzogenaurach is close to Nuremberg, the DATEV crowd knows it. Keep it short.
-->

---

<!-- _class: light reveal -->

<!--
Notes:
- One line per click (HTML deck only). Set expectations: this is not theory and not a framework, it is what I saw work and fail in real teams, including nights on call.
-->

## What to Expect: Best Practices from the Field
* What I have seen myself in **10 years** in multiple teams
* Including **being on call** myself
* Take what fits your team

---

<!-- _class: light -->

<!--
Notes:
- TODO: create the Mentimeter, add code and QR code (assets/mentimeter-qr-datev.png), then replace the TODO below.
- Suggested questions, as on the slide. Look at the live results together, then use them in the lifecycle part.
-->

## Help Me Understand Your Process

Go to [menti.com](https://www.menti.com/) and enter the code **TODO**. Three anonymous questions:

<div class="flow nw xl big">
  <div class="sketch accent">1<small>How long from commit to production?</small></div>
  <div class="sketch alt">2<small>How do you stop a broken feature?</small></div>
  <div class="sketch accent">3<small>What do you have when the pager rings?</small></div>
</div>

---

<!-- _class: light -->
<!-- _header: 'The software lifecycle' -->

<!--
Notes:
- Four parts, we walk through them left to right. AI helps in every part, but only with the process in place.
-->

## Four Parts of the Lifecycle

<div class="phases">
  <div class="phase"><h3>1 · Build</h3><ul><li>Meaningful, fast test suite</li><li>Context caching</li><li>Parallelization</li><li>Testcontainers</li><li>Mutation testing</li></ul></div>
  <div class="phase"><h3>2 · Verify</h3><ul><li>SonarQube</li><li>Static code analysis</li><li>Formatting</li><li>Fast, reproducible CI/CD</li></ul></div>
  <div class="phase"><h3>3 · Ship</h3><ul><li>Fast deployments</li><li>Rolling · Blue/Green · A/B</li><li>Feature flags</li><li>Kill switch</li></ul></div>
  <div class="phase"><h3>4 · Operate</h3><ul><li>Structured logging + MDC</li><li>Distributed tracing</li><li>Alerting</li><li>Runbooks</li><li>Canary tests</li></ul></div>
</div>

<div class="ribbon">AI and skills across all four parts</div>

---

<!-- _class: light section -->
<!-- _header: 'Part 1: Build' -->

## 01 - Build

# A meaningful, fast test suite

<!--
Notes:
- The test suite is the safety net. If it is slow or meaningless, nobody trusts it.
-->

---

<!-- _class: light statement -->

<!--
Notes:
- The test suite is the safety net. A net with holes or a net that takes an hour to check does not catch you. It must be fast AND reliable.
-->

# The test suite is your **safety net**. It must be **fast** and **reliable**.

---

<!-- _class: light -->

<!--
Notes:
- Fast: we only look at the three levers on a high level, in the next slides. Reliable: this needs knowledge and judgment. What to test, which slice, how to avoid flaky tests. I write these best practices down as skills.
-->

## Fast and Reliable

<div class="phases two">
  <div class="phase hot"><h3>Fast</h3><ul><li>Parallelization</li><li>Context caching</li><li>Testcontainers optimizations</li><li><em>high level, next slides</em></li></ul></div>
  <div class="phase"><h3>Reliable</h3><ul><li>Needs <strong>knowledge</strong> and <strong>judgment</strong></li><li>What to test, which slice, no flaky tests</li><li>Mutation testing</li><li>Best practices become <strong>skills</strong></li></ul></div>
</div>

---

<!-- _class: light -->

<!--
Notes:
- Same configuration means a cache hit. @MockitoBean, @DirtiesContext, different properties or profiles create a new context.
- Spring Test Profiler shows how many contexts you create.
-->

## Context Caching: Start Spring Once

<div class="flow nw xl">
  <div class="sketch">Test class A</div>
  <div class="sketch">Test class B</div>
  <div class="sketch">Test class C</div>
  <div class="arrow">→</div>
  <div class="sketch accent">1 cached<br>ApplicationContext<small>same config = cache hit</small></div>
</div>

<div class="flow nw xl bad">
  <div class="sketch">@MockitoBean</div>
  <div class="sketch">@DirtiesContext</div>
  <div class="sketch">@ActiveProfiles / properties</div>
  <div class="arrow">→</div>
  <div class="sketch">new context = slow<small>every new context costs seconds</small></div>
</div>

---

<!-- _class: light cards2 -->

<!--
Notes:
- Parallel: JUnit 5 parallel execution, Maven forks. Containers: singleton per image, @ServiceConnection, reuse across test classes.
-->

## Parallel Tests and Faster Containers

* **Test parallelization**<small>JUnit 5 parallel mode and Maven forks, independent tests only</small>
* **Testcontainers**<small>one container per image, @ServiceConnection, shared across classes</small>

---

<!-- _class: light -->

<!--
Notes:
- Mutation testing answers: would my tests notice a bug? PIT changes the code (flips a condition, removes a call) and runs the tests. A surviving mutant is a gap. Great check for AI-written tests.
-->

## Mutation Testing: Do Your Tests Notice Bugs?

<div class="flow nw xl">
  <div class="sketch">Your code</div>
  <div class="arrow">→</div>
  <div class="sketch accent">PIT changes it<small>flip a condition, drop a call</small></div>
  <div class="arrow">→</div>
  <div class="sketch">Run the tests</div>
</div>

<div class="flow nw xl">
  <div class="sketch accent">Test fails = mutant killed<small>the test has teeth</small></div>
  <div class="sketch alt" style="border-color:#b91c1c;color:#b91c1c">Tests pass = mutant survived<small>a gap in your safety net</small></div>
</div>

---

<!-- _class: light -->

<!--
Notes:
- Reliable tests need judgment. I wrote mine down as a skill library for Spring Boot testing, so an AI agent follows the same rules I would. Same structure as in the Prompt It Right talk.
-->

## My Skill Library for Spring Boot Testing

```text
.claude/skills/spring-boot-testing/
├── unit-testing            fast tests without context bloat
├── slice-testing           right-sized Spring context slices
├── slice-web-testing       web layer with @WebMvcTest
├── integration-testing     full-context tests that stay fast
├── testcontainers-setup    real infrastructure, one container per image
├── e2e-ui-testing          user journeys against the running app
└── test-setup-review       flags test anti-patterns for you
```

Rules, best practices and anti-patterns, written down **once**. The agent follows **your** judgment.

---

<!-- _class: light section -->
<!-- _header: 'Part 2: Verify' -->

## 02 - Verify

# Check everything you can before it ships

---

<!-- _class: light reveal cards3 -->

<!--
Notes:
- Everything that a machine can check, a machine should check, on every commit.
-->

## Automate Every Check You Can

* **SonarQube**<small>code quality and security hotspots</small>
* **Static analysis**<small>Error Prone · SpotBugs · ArchUnit</small>
* **Formatting**<small>Spotless · Checkstyle</small>
* **Dependencies**<small>Renovate · Dependabot</small>
* **Secret scanning**<small>no keys in Git</small>
* **Quality gates**<small>fail the build, not production</small>

---

<!-- _class: light -->

<!--
Notes:
- Fast and reproducible: same result on every run, no snowflake build server, triggerable at any time, also on Friday.
-->

## CI/CD: Fast, Reproducible, Always Triggerable

<div class="flow nw xl">
  <div class="sketch">Commit</div>
  <div class="arrow">→</div>
  <div class="sketch accent">Build</div>
  <div class="arrow">→</div>
  <div class="sketch accent">Test</div>
  <div class="arrow">→</div>
  <div class="sketch accent">Analyze</div>
  <div class="arrow">→</div>
  <div class="sketch accent">Package</div>
  <div class="arrow">→</div>
  <div class="sketch">Deploy</div>
</div>

<div class="flow nw xl">
  <div class="sketch alt">Fast<small>minutes, not an hour</small></div>
  <div class="sketch">Reproducible<small>same input, same result</small></div>
  <div class="sketch alt">On demand<small>any time, any day</small></div>
</div>

---

<!-- _class: light section -->
<!-- _header: 'Part 3: Ship' -->

## 03 - Ship

# Deploy is not release

---

<!-- _class: light reveal cards3 -->

<!--
Notes:
- Rolling: replace instances step by step. Blue/green: two environments, switch traffic, switch back if needed. A/B or canary release: a small share of traffic gets the new version first.
-->

## Fast Deployment Strategies

* **Rolling update**<small>replace instances step by step</small>
* **Blue / Green**<small>two environments, switch traffic</small>
* **A/B and canary release**<small>a small share of traffic first</small>

---

<!-- _class: light -->

<!--
Notes:
- Togglz is a Java library with a Spring Boot starter and an admin console (dashboard). You can switch features on and off at runtime and use activation strategies, for example by username, by role or gradual rollout. Verify the exact strategy names against the current docs before the talk.
- More advanced: LaunchDarkly (managed service, targeting, experiments).
- TODO: check the Togglz version and starter coordinates for Spring Boot 4.
-->

## Feature Flags with Togglz

```java {4-7}
public enum Features implements Feature {

    @Label("New checkout flow")
    NEW_CHECKOUT;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
```

Dashboard: switch on for **key users** or **roles** first, then everyone. More advanced: **LaunchDarkly**.

---

<!-- _class: light -->

<!--
Notes:
- Official screenshot from togglz.org (Togglz 2.0, old look, the console is still the same idea). The strategies visible here: gradual rollout (10 percent) and users by name. Credit togglz.org. TODO: replace with a fresh screenshot of the demo if time allows.
-->

## The Togglz Admin Console

![bg right:50% fit](assets/togglz-admin-console.png)

- Every feature with its **status**
- **Strategy** per feature: gradual rollout, users by name, roles
- Switch **on or off at runtime**, no deployment

<small>Screenshot: togglz.org</small>

---

<!-- _class: light -->

<!--
Notes:
- A rollback needs a new pipeline run and a deployment. A flag switch takes seconds.
-->

## Kill Switch: Faster Than a Rollback

<div class="flow nw xl bad">
  <div class="sketch">Rollback<small>revert · pipeline · deploy · verify</small></div>
  <div class="arrow">=</div>
  <div class="sketch">minutes</div>
</div>

<div class="flow nw xl">
  <div class="sketch accent">Flip the flag off<small>one click in the dashboard</small></div>
  <div class="arrow">=</div>
  <div class="sketch accent">seconds</div>
</div>

---

<!-- _class: light section -->
<!-- _header: 'Part 4: Operate' -->

## 04 - Operate

# See it, find it, fix it

---

<!-- _class: light -->

<!--
Notes:
- Spring Boot supports structured logging out of the box (ECS, Logstash, GELF). MDC entries are added to the JSON. A filter puts the user ID into the MDC so every log line carries it. Clear the MDC afterwards.
- TODO: verify the property and field names with the Spring Boot version used in the demo.
-->

## Structured Logging and MDC

```yaml
logging.structured.format.console: ecs
```

```java {1,3}
MDC.put("userId", authentication.getName());
try { chain.doFilter(request, response); }
finally { MDC.clear(); }
```

```json
{"log.level":"ERROR","message":"Payment failed","userId":"u-4711","trace.id":"4bf92f35"}
```

Now the log query `userId:u-4711` finds exactly what this user did.

---

<!-- _class: light -->

<!--
Notes:
- Illustrative trace: one request across the HTTP layer, service, database and an external call. The slow span shows where the problem is. Replace with a real screenshot (Grafana Tempo, Jaeger, Zipkin) from the demo.
-->

## Distributed Tracing: Where Is the Problem?

<div class="spans">
  <div class="row"><div class="label">POST /orders</div><div class="track"><div class="bar" style="width:100%">2.4 s</div></div></div>
  <div class="row"><div class="label">OrderService.place()</div><div class="track"><div class="bar" style="width:92%;margin-left:4%">2.3 s</div></div></div>
  <div class="row"><div class="label">SELECT customer</div><div class="track"><div class="bar" style="width:6%;margin-left:6%"></div></div></div>
  <div class="row"><div class="label">POST payment-service</div><div class="track"><div class="bar slow" style="width:78%;margin-left:14%">2.0 s</div></div></div>
  <div class="row"><div class="label">INSERT order</div><div class="track"><div class="bar" style="width:5%;margin-left:93%"></div></div></div>
</div>

From the HTTP call through the services and the database and back: **one trace shows the slow span**.

---

<!-- _class: light -->

<!--
Notes:
- Alert on symptoms users feel (error rate, latency, failed canary), not on every CPU spike. The alert message links straight to the runbook.
-->

## Alerting and Runbooks

<div class="flow nw xl">
  <div class="sketch accent">Alert<small>error rate · latency · canary</small></div>
  <div class="arrow">→</div>
  <div class="sketch">On-call<small>the right person</small></div>
  <div class="arrow">→</div>
  <div class="sketch accent">Runbook<small>known first steps</small></div>
  <div class="arrow">→</div>
  <div class="sketch">Fix or flag off</div>
</div>

<div class="ribbon">A runbook: symptom · impact · first checks with deep links to logs, traces and dashboards · mitigation · escalation</div>

---

<!-- _class: light -->

<!--
Notes:
- We cannot test everything before production. Canary tests are end-to-end tests that run all the time, ideally in production, with a dedicated test user. They give true feedback and can trigger the alert before a customer calls.
-->

## Canary Tests: Test in Production, Continuously

<div class="flow nw xl">
  <div class="sketch">Scheduler<small>every few minutes</small></div>
  <div class="arrow">→</div>
  <div class="sketch accent">Test user<small>real user journeys</small></div>
  <div class="arrow">→</div>
  <div class="sketch">Production</div>
  <div class="arrow">→</div>
  <div class="sketch accent">Assert + alert<small>before users notice</small></div>
</div>

<div class="ribbon">End-to-end tests that run all the time: true feedback from production</div>

---

<!-- _class: light section -->
<!-- _header: 'AI and skills' -->

## 05 - The Role of AI

# AI gets us there faster

---

<!-- _class: light reveal cards3 -->

<!--
Notes:
- AI can write code and tests, but the judgment must be written down once as skills: what makes a meaningful test, how we structure it, how we log. Then the agent follows our rules.
-->

## Skills Carry Your Judgment

* **Test skill**<small>what makes a meaningful test</small>
* **Structure skill**<small>how we layer and name tests</small>
* **Logging skill**<small>JSON logs, MDC, no secrets</small>
* **Review skill**<small>flags test anti-patterns</small>
* **Runbook skill**<small>draft from alert and code</small>
* **Pipeline skill**<small>keep it fast and reproducible</small>

---

<!-- _class: light reveal cards4 big -->

<!--
Notes:
- Summary: the big toolkit, one box per click (HTML deck only). Together they give confidence in every commit.
-->

* **Fast tests**<small>meaningful and cached</small>
* **Static checks**<small>Sonar · formatting</small>
* **CI/CD**<small>fast and reproducible</small>
* **Feature flags**<small>deploy is not release</small>
* **Logs and traces**<small>JSON · MDC · spans</small>
* **Alerting**<small>the right people, fast</small>
* **Runbooks**<small>know what to do</small>
* **Canary tests**<small>true feedback</small>

---

<!-- _class: light statement -->

# You will ship a bug. **How fast you recover is what counts.**

---

<!-- _class: light statement reveal -->
<!-- _paginate: false -->

# You can delegate the typing.
* You can't delegate the ownership.
* **You** get paged at 3 AM.
* Invest in the processes that give you **confidence in every commit**.

---

<!-- _class: light -->

<!--
Notes:
- Soft pointer. Replace or remove what does not fit the DATEV audience.
-->

## Learn More

![bg right:36% fit](assets/agentic-testing-course.png)

- **Newsletter and blog** on testing Spring Boot applications: [rieckpil.de](https://rieckpil.de)
- Online course: **Agentic Testing for Spring Boot**
- Spring Test Profiler for context caching insights
- TODO: add links to Togglz, PIT and the demo repository

---

<!-- _class: light closing -->
<!-- _paginate: false -->
<!-- _header: '' -->
<!-- _footer: '' -->

![bg right:33%](assets/end.jpg)

# Fearless Shipping!

Thank you! Questions?

- [LinkedIn: linkedin.com/in/rieckpil](https://www.linkedin.com/in/rieckpil)
- [Mail: philip@pragmatech.digital](mailto:philip@pragmatech.digital)

Spring Boot testing newsletter: **rieckpil.de/newsletter**

![h:200](assets/newsletter-qr.png)
