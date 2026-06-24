package main.DesignPractice.RateLimitter.TokenBucket;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
  private final TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter();

  @GetMapping("/profile")
  public ResponseEntity<String> getProfile(
      @RequestHeader("User-Id") String userId) {

    if (!rateLimiter.allow(userId)) {

      return ResponseEntity
          .status(429)
          .body("Too Many Requests");
    }

    return ResponseEntity.ok("User Profile");
  }
}
