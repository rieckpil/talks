package de.rieckpil.talks.advanced;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Disabled
class SelenideTest {

  @Test
  void testPage() {
    System.setProperty("selenide.browser", "Firefox");

    open("https://rieckpil.de");

    System.out.println("### h1 ### " + $(Selectors.byTagName("h1")).getText());

    ElementsCollection allParagraphs = $$(Selectors.byTagName("p"));

    allParagraphs
      .forEach(paragraph -> System.out.println(paragraph.getOwnText()));
  }
}
