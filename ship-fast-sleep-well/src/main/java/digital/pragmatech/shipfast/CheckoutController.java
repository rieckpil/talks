package digital.pragmatech.shipfast;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

  @GetMapping("/checkout")
  public String checkout() {
    if (Features.NEW_CHECKOUT.isActive()) {
      return "New checkout flow";
    }
    return "Classic checkout flow";
  }
}
