package main.DSA;
import java.util.Map;
import java.util.HashMap;

public class CountNumberofDivisibleTripletSums {

  // Count triplets (i, j, k) such that:
  // i < j < k
  // (nums[i] + nums[j] + nums[k]) % d == 0
  static long divisibleTripletCount(int[] nums, int d) {

    Map<Integer, Integer> cnt = new HashMap<>();

    long ans = 0;

    // nums[0] is the first possible i
    int remainder = nums[0] % d;
    cnt.put(remainder, 1);

    // Start j from index 1
    for (int j = 1; j < nums.length; j++) {

      // k must be to the right of j
      for (int k = j + 1; k < nums.length; k++) {

        // Remainder of nums[j] + nums[k]
        int remainderJK = (nums[j] + nums[k]) % d;

        // Remainder needed from nums[i]
        int required = (d - remainderJK) % d;

        // Number of previous elements having that remainder
        ans += cnt.getOrDefault(required, 0);
      }

      // Now nums[j] becomes an i candidate
      // for the NEXT j
      int currentRemainder = nums[j] % d;

      cnt.put(
          currentRemainder,
          cnt.getOrDefault(currentRemainder, 0) + 1
      );
    }

    return ans;
  }

  public static void main(String[] args) {

    int[] arr = {1, 2, 3, 4, 5};

    int d = 3;

    long count = divisibleTripletCount(arr, d);

    System.out.println(
        "The number of divisible triplets is: "
        + count
    );
  }
}
