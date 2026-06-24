package main.DesignPractice.ExternalApiCall;

import java.io.IOException;

public class OrderService {

  private final ExternalHttpClient externalHttpClient = new ExternalHttpClient();

  public void processOrder(int userId) {
    try {
      String user = externalHttpClient.getUser(userId);
      System.out.println("Fetched User");
      System.out.println(user);
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  public void createPost(String json){
    try {
      String response = externalHttpClient.createPost(json);
      System.out.println(response);
    }catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }
}
