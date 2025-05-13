package de.rieckpil.talks.diffblue;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {FibonacciController.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class FibonacciControllerDiffblueTest {
  @Autowired
  private FibonacciController fibonacciController;

  @MockitoBean
  private FibonacciService fibonacciService;

  /**
   * Test {@link FibonacciController#fibonacci(int, int)}.
   * <p>
   * Method under test: {@link FibonacciController#fibonacci(int, int)}
   */
  @Test
  @DisplayName("Test fibonacci(int, int)")
  @Tag("MaintainedByDiffblue")
  void testFibonacci() throws Exception {
    // Arrange
    when(fibonacciService.sequenceToNth(anyInt())).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/fibonacci");

    // Act and Assert
    MockMvcBuilders.standaloneSetup(fibonacciController)
      .build()
      .perform(requestBuilder)
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
      .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
}
