# Testing standards: unit tests (sample, condensed)

Severity: **C** blocks the change, **H** must fix, **M** fix or justify, **L** mention only.

| ID | Sev | Rule | Why |
|---|---|---|---|
| T1 | C | Every behavior change ships with a unit test that reproduces it | A fix without a test comes back |
| T2 | H | One assertion chain per test, no `SoftAssertions` | One reason to fail |
| T3 | H | The assertion is the last statement of the test | Arrange, act, assert stays readable |
| T5 | H | Every test asserts something | A test without assertion proves nothing |
| T7 | H | Every assertion has a failure message | The 3 AM message must locate the defect |
| T9 | H | No test-class fields | Shared state breaks parallel runs |
| T10 | H | No `@BeforeEach`, `@Mock`, `@InjectMocks` | Each test builds what it needs |
| T11 | H | No shared constants or fixtures, literals live in the test | The test explains itself |
| T13 | H | One test verifies one behavior | The test name is a sentence about that behavior |
| T17 | L | Do not test getters, setters, constructors, records | No logic, no value |
| T27 | H | Waits have a timeout, never `Thread.sleep` | Flaky and slow |
| U1 | H | No Spring context | Context start costs seconds |
| U2 | H | Time comes from an injected `Clock` | Deterministic tests |
| U5 | H | Call only the public API of the class under test | No reflection, no test hooks |
| U7 | M | No unit test full of mocks to satisfy a request | It proves that Mockito works |
| U8 | H | Order independent and parallel safe | Random order and concurrent runs find hidden coupling |

This is a sample. The full catalog of my library has more than three times as many rules.
