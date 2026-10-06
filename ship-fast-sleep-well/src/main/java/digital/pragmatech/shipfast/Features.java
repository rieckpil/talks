package digital.pragmatech.shipfast;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.InfoLink;
import org.togglz.core.annotation.Label;
import org.togglz.core.annotation.Owner;
import org.togglz.core.context.FeatureContext;

public enum Features implements Feature {

  @Label("New checkout flow")
  @Owner("team-checkout")
  @InfoLink("https://pragmatech.digital/")
  NEW_CHECKOUT,

  @Label("Pay by card")
  @Owner("team-payment")
  @EnabledByDefault
  PAY_BY_CARD,

  @Label("Fast product search")
  @Owner("team-search")
  FAST_SEARCH;

  public boolean isActive() {
    return FeatureContext.getFeatureManager().isActive(this);
  }
}
