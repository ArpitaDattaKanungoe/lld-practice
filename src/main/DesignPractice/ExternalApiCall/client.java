package main.DesignPractice.ExternalApiCall;

public class client {

  public static void main(String[] args) {
    String json = """
        {
            "title":"Java",
            "body":"Learning HttpClient",
            "userId":1
        }
        """;
    OrderService service = new OrderService();
    service.createPost(json);
    service.processOrder(1);
  }
}
