# Golden Mockito Rules with Java & Spring Boot Examples

Let me explain each golden rule with practical Spring Boot code examples:

## 1. Do not mock types you don't own (e.g., Framework Boundary)

This rule suggests avoiding mocking external libraries or frameworks you don't control.

**Bad Practice:**
```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    RestTemplate restTemplate; // Mocking a Spring Framework class

    @InjectMocks
    OrderService orderService;

    @Test
    void shouldProcessOrder() {
        // Mocking RestTemplate methods directly
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Success", HttpStatus.OK));

        orderService.processOrder(new Order());
        // Assertions...
    }
}
```

**Better Practice:**
```java
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    PaymentGatewayClient paymentGatewayClient; // Your wrapper around RestTemplate

    @InjectMocks
    OrderService orderService;

    @Test
    void shouldProcessOrder() {
        when(paymentGatewayClient.processPayment(any(PaymentRequest.class)))
            .thenReturn(new PaymentResponse(true, "12345"));

        orderService.processOrder(new Order());
        // Assertions...
    }
}

// In production code
@Service
class PaymentGatewayClient {
    private final RestTemplate restTemplate;

    // Constructor with RestTemplate

    public PaymentResponse processPayment(PaymentRequest request) {
        // Uses RestTemplate internally
        return restTemplate.postForObject("/payments", request, PaymentResponse.class);
    }
}
```

## 2. Don't mock value objects (e.g., DTOs)

Value objects are immutable objects without behavior. Don't mock them - just create real instances.

**Bad Practice:**
```java
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository repository;

    @Mock
    CustomerDTO customerDTO; // Mocking a DTO - unnecessary and problematic

    @Test
    void shouldSaveCustomer() {
        when(customerDTO.getName()).thenReturn("John");
        when(customerDTO.getEmail()).thenReturn("john@example.com");

        customerService.saveCustomer(customerDTO);
        // Assertions...
    }
}
```

**Better Practice:**
```java
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    CustomerRepository repository;

    @InjectMocks
    CustomerService customerService;

    @Test
    void shouldSaveCustomer() {
        // Create a real DTO - it's just data
        CustomerDTO customerDTO = new CustomerDTO("John", "john@example.com");

        customerService.saveCustomer(customerDTO);

        verify(repository).save(argThat(customer ->
            customer.getName().equals("John") &&
            customer.getEmail().equals("john@example.com")));
    }
}
```

## 3. Don't mock everything

Mock only what's necessary, use real objects when possible, especially for simple classes.

**Bad Practice:**
```java
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    PriceCalculator priceCalculator; // Simple utility class that could be real

    @Mock
    DiscountService discountService;

    @InjectMocks
    ProductService productService;

    @Test
    void shouldCalculateFinalPrice() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product("Phone", 1000.0)));
        when(priceCalculator.calculateTax(1000.0)).thenReturn(100.0);
        when(discountService.getDiscount("SPRING")).thenReturn(0.1);

        double price = productService.getFinalPrice(1L, "SPRING");
        assertEquals(990.0, price);
    }
}
```

**Better Practice:**
```java
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository productRepository;

    @Mock
    DiscountService discountService; // External dependency - mock

    // Use the real calculator - it's simple with no external dependencies
    private PriceCalculator priceCalculator = new PriceCalculator();

    @InjectMocks
    ProductService productService;

    @Test
    void shouldCalculateFinalPrice() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(new Product("Phone", 1000.0)));
        when(discountService.getDiscount("SPRING")).thenReturn(0.1);

        double price = productService.getFinalPrice(1L, "SPRING");
        assertEquals(990.0, price);
    }
}
```

## 4. Show some love with your tests

Write meaningful, readable tests that accurately describe the behavior and are maintainable.

**Bad Practice:**
```java
@ExtendWith(MockitoExtension.class)
class Tst {
    @Mock
    Repo r;

    @InjectMocks
    Svc s;

    @Test
    void t1() {
        when(r.findById(1L)).thenReturn(Optional.of(new User("u", "p")));

        boolean result = s.authenticate(1L, "p");

        assertTrue(result);
    }
}
```

**Better Practice:**
```java
@ExtendWith(MockitoExtension.class)
class UserAuthenticationServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserAuthenticationService authService;

    @Test
    void shouldAuthenticateUserWithCorrectPassword() {
        // Given a user exists in the database
        User validUser = new User("testuser", "password123");
        when(userRepository.findById(1L)).thenReturn(Optional.of(validUser));

        // When authenticating with correct credentials
        boolean authResult = authService.authenticate(1L, "password123");

        // Then authentication should succeed
        assertTrue(authResult, "Authentication should succeed with correct password");
    }

    @Test
    void shouldRejectAuthenticationWithIncorrectPassword() {
        // Given a user exists in the database
        User validUser = new User("testuser", "password123");
        when(userRepository.findById(1L)).thenReturn(Optional.of(validUser));

        // When authenticating with incorrect password
        boolean authResult = authService.authenticate(1L, "wrongPassword");

        // Then authentication should fail
        assertFalse(authResult, "Authentication should fail with incorrect password");
    }

    @Test
    void shouldRejectAuthenticationForNonExistentUser() {
        // Given no user exists with ID 999
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // When trying to authenticate a non-existent user
        boolean authResult = authService.authenticate(999L, "anyPassword");

        // Then authentication should fail
        assertFalse(authResult, "Authentication should fail for non-existent users");
    }
}
```

Each of these golden rules helps create more maintainable and reliable tests by focusing on what really needs to be mocked, using real objects when appropriate, and writing clear, understandable test code that properly documents the system's behavior.
