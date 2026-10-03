# Demo 1: Review the PetClinic suite with the review skill (10 min)

Where: Part 3a. Project: `demo/spring-petclinic` (pinned commit, skills installed by `demo/setup.sh`).

## Setup
- `./demo/setup.sh`, then `cd demo/spring-petclinic`
- Start Claude Code, check `/skills` shows `spring-boot-testing`
- Run `./mvnw -q test -Dtest='!PostgresIntegrationTests'` once before (warm caches)

## Prompt
> Review the test setup of this project. Suite review mode. Do not change files.

## What to point out
1. Numbers first: 19 classes, 76 methods (7 unit, 5 `@WebMvcTest`, 1 `@DataJpaTest`, 5 `@SpringBootTest`, 0 E2E)
2. No Surefire/Failsafe split
3. `mysql:9.7` declared twice (`MySqlIntegrationTests` `@Container` + `MysqlTestApplication` `@Bean`)
4. H2 in tests while production uses MySQL/Postgres
5. 10 cached contexts (limit 9)
6. Three naming conventions, three assertion libraries
7. Wow: the 15 `@Disabled*` hits are NOT flagged (`@DisabledInNativeImage`). Rule `[R7-H]` and "Not findings" section
8. Remediation order by payoff, three worst files

## Fallback
Recorded run (see `fallbacks.md`) or the saved report in `script/demos/assets/` (TODO: save one report before the talk).
