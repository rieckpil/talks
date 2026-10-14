package de.rieckpil.talks.book;

import jakarta.validation.constraints.NotBlank;

public record BookRequest(@NotBlank String title, @NotBlank String author, @NotBlank String isbn) {
}
