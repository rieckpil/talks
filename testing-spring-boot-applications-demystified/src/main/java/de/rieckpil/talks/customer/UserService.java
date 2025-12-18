package de.rieckpil.talks.customer;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Long registerUser(String username, int age) {

    if(this.userRepository.findByUsername(username).isPresent()) {
      throw new IllegalArgumentException("Username already exists");
    }

    if(age <= 18) {
      throw new IllegalArgumentException("User is underaged");
    }

    if(username.equals("ADMIN")) {
      // default admin user id
      return 3L;
    }

    return userRepository.save(username);
  }
}
