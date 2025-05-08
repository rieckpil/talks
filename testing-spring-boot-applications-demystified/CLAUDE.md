# Claude Instructions

## Test Code

When writing test code, please follow these guidelines:

- Use JUnit 5 for all test classes
- Name test methods using the pattern: `should<ExpectedBehavior>When<Condition>`
- Structure each test with the Arrange-Act-Assert pattern
- Use AssertJ for all assertions
- Avoid for loops and if statements in tests
- Avoid comments in the test code
- Name the class under test variable as cut
- Create a separate test class for each production class
- Follow a consistent setup pattern for all tests
- Use @DisplayName for more descriptive test names in reports
- Group related tests with @Nested classes
- Use parameterized tests for testing multiple scenarios
- Mock external dependencies with Mockito
- Use Java records for test data classes
- When constructing test data for existing domain classes, use the Builder and Object Mother pattern
- Use Java text blocks for larger JSON data
