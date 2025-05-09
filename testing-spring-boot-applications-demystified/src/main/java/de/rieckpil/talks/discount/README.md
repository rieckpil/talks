# Mutation Testing Example

This package demonstrates the value of mutation testing with PIT in a Spring Boot application.

## Key Components

1. **DiscountCalculator.java**: Business logic class that calculates discounts based on various conditions:
   - Standard discount (5%)
   - Premium discount for orders over $1000 (10% additional)
   - First-time customer bonus (2% additional)
   - Seasonal promotion discount (3% additional)

2. **Customer.java**: Simple record class representing a customer with a flag for first-time status.

3. **Testing Approaches**:
   - **DiscountCalculatorPoorTest.java**: Demonstrates tests with 100% line coverage but weak assertions
   - **DiscountCalculatorGoodTest.java**: Shows proper tests with strong assertions and systematic test cases

## Understanding the Example

### Traditional Code Coverage (JaCoCo)

- Both test classes would show **100% line coverage** because they execute all code paths
- JaCoCo cannot detect the quality difference between these test suites

### Mutation Testing (PIT)

When running PIT against these tests:

1. **Poor Tests**:
   - High line coverage but **low mutation coverage (~45%)**
   - Many mutations survive despite tests passing
   - Critical business logic flaws could reach production

2. **Good Tests**:
   - High line coverage and **high mutation coverage (~97%)**
   - Almost all mutations are killed
   - Much greater confidence in the correctness of the business logic

## Running the Tests

To run JaCoCo and PIT:

```bash
# Run tests with JaCoCo coverage report
mvn clean test jacoco:report

# Run tests with PIT mutation coverage
mvn org.pitest:pitest-maven:mutationCoverage
```

## Key Insights for Presentation

1. **False Security**: High code coverage can give a false sense of security if assertions are weak
2. **Edge Cases**: Mutation testing reveals missed edge cases (like boundary conditions)
3. **Better Tests**: Writing tests that kill mutations leads to more robust code
4. **Integration**: PIT integrates easily with Maven/Gradle build processes
5. **Scalability**: For large codebases, incremental analysis can be used (see pit-incremental profile)