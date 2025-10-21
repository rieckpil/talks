package de.rieckpil.talks.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CustomerCreationRequest(
  @NotEmpty String firstName, @NotEmpty String lastName, @Email String email
) {
}
