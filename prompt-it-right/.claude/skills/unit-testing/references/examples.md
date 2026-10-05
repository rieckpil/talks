# Examples: good and bad pairs (sample)

## T7: failure message on every assertion

```java
// bad: tells you nothing at 3 AM
assertThat(order.status()).isEqualTo(PLACED);

// good: one chain, a message that locates the defect
assertThat(order.status())
  .as("Order is placed after a successful payment")
  .isEqualTo(PLACED);
```

## T9 / T10: no shared state

```java
// bad: fields and @BeforeEach shared by all tests
class OrderServiceTest {
  private OrderRepository repository;
  @BeforeEach void setUp() { repository = mock(OrderRepository.class); }
}

// good: every test builds its own collaborators
@Test
void shouldRejectOrderWhenStockIsZero() {
  OrderRepository repository = mock(OrderRepository.class);
  OrderService cut = new OrderService(repository, Clock.fixed(NOW, UTC));
  // ...
}
```

## U2: time from a Clock

```java
// bad: depends on the wall clock
Instant placedAt = Instant.now();

// good: injected Clock, fixed in the test
Instant placedAt = clock.instant();
```
