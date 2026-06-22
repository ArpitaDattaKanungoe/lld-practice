package DesignPractice.DesignHashMap;

public class Main {

  public static void main(String[] args) {

    CustomHashMap<String, Integer> map = new CustomHashMap<>();

    map.put("Shubh", 90);
    map.put("Karan", 80);
    map.put("Alice", 85);
    map.put("John", 78);
    map.put("Tom", 82);
    map.put("Parth", 95);

    System.out.println("John : " + map.get("John"));
    System.out.println("Bob  : " + map.get("Bob"));

    map.put("John", 99);

    System.out.println("Updated John : " + map.get("John"));

    System.out.println("Contains Alice : " + map.containsKey("Alice"));
    System.out.println("Contains Bob   : " + map.containsKey("Bob"));

    System.out.println("Removed Tom : " + map.remove("Tom"));

    System.out.println("Tom : " + map.get("Tom"));

    System.out.println("Size : " + map.size());

    System.out.println("Is Empty : " + map.isEmpty());
  }
}
