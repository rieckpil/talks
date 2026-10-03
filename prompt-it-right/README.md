# Prompt It Right: Spring Boot Testing in the AI Era

Workshop-style talk (180 min incl. 30 min break) for Devoxx Belgium 2026.

Part 1 is a crash course on Spring Boot testing. Part 2 explains why a fast, complete test suite is the key for agentic development. Part 3 shows how Philip structures testing skills, uses MCP servers and keeps the pipeline fast.

See [TALK-PLAN.md](TALK-PLAN.md) for the timeline and [script/](script/) for the speaker scripts.

## Layout

| Path | Content |
|---|---|
| `slides/content.md` | The Marp deck (PragmaTech theme, light) |
| `slides/README.md` | Build commands |
| `script/` | Speaker script per block, run sheet, demo scripts |
| `demo/setup.sh` | Clones PetClinic (pinned) and installs the testing skills |
| `src/`, `pom.xml` | Spring Boot 4 / Java 25 project with the crash course samples: `CustomerController` + `CustomerControllerTest` (slice test) and `CustomerControllerUnitTests`. Build: `JAVA_HOME=<jdk25> ./mvnw verify` (also runs in the GitHub Actions matrix) |

## Build the slides

```bash
cd slides
marp -p -w content.md < /dev/null          # live preview
marp content.md -o content.html < /dev/null
./resize_images.sh && ./generate_sharable_pdf.sh slides-devoxx-be-2026-10-05.pdf
```

## Demo preparation checklist

- [ ] `./demo/setup.sh`
- [ ] `cd demo/spring-petclinic && ./mvnw -q test -Dtest='!PostgresIntegrationTests'` is green
- [ ] Docker running, port 5432 free, images pre-pulled (`mysql:9.7`, `postgres`)
- [ ] Claude Code logged in, skills visible with `/skills`
- [ ] MCP servers added and tested (Context7, GitHub, Playwright)
- [ ] Terminal font size 20+, notifications off
- [ ] Fallback recordings ready (`script/demos/fallbacks.md`)
