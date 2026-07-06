package main.DesignPractice.SplitWiseLLD;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class SplitwiseSettlement {

  public static void settle(Map<String, Integer> netBalance) {

    Queue<Balance> debtors = new LinkedList<>();
    Queue<Balance> creditors = new LinkedList<>();

    // Separate debtors and creditors
    for (Map.Entry<String, Integer> entry : netBalance.entrySet()) {

      String person = entry.getKey();
      int balance = entry.getValue();

      if (balance < 0) {
        debtors.offer(new Balance(person, -balance)); // owes money
      } else if (balance > 0) {
        creditors.offer(new Balance(person, balance)); // should receive
      }
    }

    while (!debtors.isEmpty() && !creditors.isEmpty()) {

      Balance debtor = debtors.peek();
      Balance creditor = creditors.peek();

      int amount = Math.min(debtor.amount, creditor.amount);

      System.out.println(
          debtor.person + " pays " +
          amount +
          " to " +
          creditor.person
      );

      debtor.amount -= amount;
      creditor.amount -= amount;

      if (debtor.amount == 0) {
        debtors.poll();
      }

      if (creditor.amount == 0) {
        creditors.poll();
      }
    }
  }

  static class Balance {
    String person;
    int amount;

    Balance(String person, int amount) {
      this.person = person;
      this.amount = amount;
    }
  }

  public static void main(String[] args) {

    Map<String, Integer> netBalance = new LinkedHashMap<>();

    netBalance.put("A", -150);
    netBalance.put("D", -50);
    netBalance.put("B", 170);
    netBalance.put("C", 30);

    settle(netBalance);
  }
}

//if we needed to netBalance map
//
//Map<String, Integer> netBalance = new HashMap<>();
//
//for (Expense expense : expenses) {
//
//String debtor = expense.from;
//String creditor = expense.to;
//int amount = expense.amount;
//
//    netBalance.put(
//    debtor,
//    netBalance.getOrDefault(debtor, 0) - amount);
//
//        netBalance.put(
//    creditor, netBalance.getOrDefault(creditor, 0) + amount);
//}
