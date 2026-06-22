package DesignPractice.ExternalApiCall;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalHttpClient {

  private final HttpClient client;

  public ExternalHttpClient() {
    this.client = HttpClientProvider.getClient();
  }

  public String getUser(int id) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id))
        .header("Accept", "application/json")
        .GET()
        .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if (response.statusCode() != 200) {
      throw new RuntimeException("API failed");
    }
    return response.body();
  }

  public String createPost(String json) throws IOException, InterruptedException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(
            "https://jsonplaceholder.typicode.com/posts"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json)) // BodyPublishers Streams the request-body to the server (used mainly for POST/PUT/PATCH)
        .build();

    HttpResponse<String> response =
        client.send(request, HttpResponse.BodyHandlers.ofString()); // BodySubscriber used in response, Consumes the response body bytes
    // and converts them into a result (e.g., String, byte[], file)

    return response.body();  }
}
