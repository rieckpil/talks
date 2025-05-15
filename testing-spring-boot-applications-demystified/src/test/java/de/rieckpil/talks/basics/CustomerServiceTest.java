package de.rieckpil.talks.basics;

import de.rieckpil.talks.CustomerRepository;
import de.rieckpil.talks.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerService customerService;

  @Test
  void shouldNotifyAllCustomersViaEmail() {

    when(customerRepository.findAllCustomerIds())
      .thenReturn(List.of("42"));

    customerService.notifyAllCustomers();

    // assert the outcome
  }
}

