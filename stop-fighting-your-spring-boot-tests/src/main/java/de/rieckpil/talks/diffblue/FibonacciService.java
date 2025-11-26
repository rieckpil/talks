package de.rieckpil.talks.diffblue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FibonacciService {

  public List<Integer> sequenceToNth(int n) {
    if (n < 0) {
      throw new IllegalArgumentException();
    }

    List<Integer> sequence = new ArrayList<>(n);

    int prev = 0, next = 1;
    while (sequence.size() < n) {
      sequence.add(next);
      int sum = prev + next;
      prev = next;
      next = sum;
    }

    return sequence;
  }
}
