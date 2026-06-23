package DesignPractice.RateLimitter.SlidingWindowLog;

public class OtpService {

  private final SlidingWindowLogRateLimiter rateLimiter = SlidingWindowLogRateLimiter.getInstance();

  public void sendOtp(String userId) {

    long now = System.currentTimeMillis() / 1000;

    if (!rateLimiter.canAllow(userId, now)) {
      System.out.println("Too many OTP requests");
      return;
    }

    String otp = generateOtp();
    saveOtp(userId, otp);
    sendSms(userId, otp);
    System.out.println("OTP sent");
  }

  private String generateOtp() {
    return "123456";
  }

  private void saveOtp(String userId, String otp) {
    System.out.println("Saving OTP for user : " + userId + " otp is : " + otp);
  }

  private void sendSms(String userId, String otp) {
    System.out.println("Sending SMS");
  }
}
