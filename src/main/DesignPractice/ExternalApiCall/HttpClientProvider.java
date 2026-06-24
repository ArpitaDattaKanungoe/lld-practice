package main.DesignPractice.ExternalApiCall;

import java.net.http.HttpClient;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class HttpClientProvider {

  private static final HttpClient CLIENT = HttpClient.newBuilder()
      .connectTimeout(Duration.of(10, ChronoUnit.SECONDS)).build();

  public static HttpClient getClient() {
    return CLIENT;
  }
}
