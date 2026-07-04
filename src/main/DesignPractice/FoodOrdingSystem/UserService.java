package main.DesignPractice.FoodOrdingSystem;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Customer login(String email, String password) {

    Customer customer = userRepository.findByEmail(email);

    if (customer == null) {
      throw new RuntimeException("User not found");
    }

    // Password validation skipped for simplicity

    return customer;
  }

  public Customer getCustomer(String customerId) {

    Customer customer = userRepository.findById(customerId);

    if (customer == null) {
      throw new RuntimeException("Customer not found");
    }

    return customer;
  }

  public void updateCustomer(Customer customer) {
    userRepository.save(customer);
  }
}
