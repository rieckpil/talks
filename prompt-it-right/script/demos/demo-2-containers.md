# Demo 2: One container per image (6 min)

Where: block 2, parallel and cache part.

## Setup
- Docker running, `mysql:9.7` pulled
- Second terminal for `docker events`

## Steps
1. Show the two MySQL declarations (`MySqlIntegrationTests`, `MysqlTestApplication`)
2. Terminal 2: `docker events --filter event=start --format '{{.Actor.Attributes.image}}' | tee events.log`
3. Run the MySQL tests: `./mvnw -q test -Dtest='MySqlIntegrationTests,MysqlTestApplication*'`
4. Count: `sort events.log | uniq -c` shows two MySQL starts
5. Prompt the agent: "Refactor to one `@TestConfiguration` with one static MySQL container and `@ServiceConnection`. Follow the testcontainers skill."
6. Rerun, count again: one start
7. Point out rules `[C1]`, `[C5]` (no fixed ports) and that reuse is never committed `[C9]`

## Risk
- Known issue: `PostgresIntegrationTests` fails when port 5432 is busy. Exclude it with `-Dtest='!PostgresIntegrationTests'`.
- If Docker is slow: show the recorded output.
