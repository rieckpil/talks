package de.rieckpil.talks.diffblue;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FibonacciController {

  private final FibonacciService service;

  public FibonacciController(FibonacciService service) {
    this.service = service;
  }

  @GetMapping("/fibonacci")
  public List<Integer> fibonacci(
    @RequestParam(required = false, defaultValue = "0") int start,
    @RequestParam(required = false, defaultValue = "10") int count
  ) {
    List<Integer> sequence = service.sequenceToNth(start + count);

    return sequence.subList(start, sequence.size());
  }
}

