package main.DesignPractice.FoodOrdingSystem;

import java.util.List;

public class RestaurantService {

  private final RestaurantRepository restaurantRepository;

  public RestaurantService(RestaurantRepository restaurantRepository) {
    this.restaurantRepository = restaurantRepository;
  }

  public List<Restaurant> searchRestaurant(Address address) {
    return restaurantRepository.findByLocation(address);
  }

  public Restaurant getRestaurant(String restaurantId) {

    Restaurant restaurant =
        restaurantRepository.findById(restaurantId);

    if (restaurant == null) {
      throw new RuntimeException("Restaurant not found");
    }

    return restaurant;
  }

  public List<MenuItem> getMenu(String restaurantId) {

    Restaurant restaurant = getRestaurant(restaurantId);

    return restaurant.getMenu();
  }

  public void validateRestaurant(Restaurant restaurant) {

    if (restaurant == null) {
      throw new RuntimeException("Restaurant not found");
    }

    if (restaurant.getMenu().isEmpty()) {
      throw new RuntimeException("Restaurant has no menu");
    }
  }
}
