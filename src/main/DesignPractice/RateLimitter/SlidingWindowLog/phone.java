package main.DesignPractice.RateLimitter.SlidingWindowLog;

public class phone {

  public static void main(String[] args) throws InterruptedException {
    OtpService otp = new OtpService();


    Thread thread1 = new Thread(()-> otp.sendOtp("Lucifer"));
    Thread thread2 = new Thread(()-> otp.sendOtp("Lucifer"));
    Thread thread3 = new Thread(()-> otp.sendOtp("Lucifer"));
    Thread thread4 = new Thread(()-> otp.sendOtp("Lucifer"));

    thread1.start();
    thread2.start();
    thread3.start();
    thread4.start();

//    ExecutorService executor = Executors.newFixedThreadPool(5);
//    for(int i=0; i<5; i++){
//      executor.submit(()-> otp.sendOtp("Lucifer"));
//    }
//    executor.awaitTermination(5, TimeUnit.SECONDS);
//    executor.shutdown();

//    OR

//    IntStream.range(0, 5)
//        .parallel()
//        .forEach(i -> otp.sendOtp("Lucifer"));
  }
}