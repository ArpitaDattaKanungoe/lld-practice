package main.DesignPractice;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
  private final int capacity;
  private int minFrequency;

  private final Map<Integer, Node> keyToNode; // NodeMapping (key -> Node)
  private final Map<Integer, DoublyLinkedList> freqToList; // // freq -> DLList mapping

  class Node {
    int key;
    int value;
    int frequency;
    Node prev;
    Node next;

    Node(int key, int value) {
      this.key = key;
      this.value = value;
      this.frequency = 1;
    }
  }

  class DoublyLinkedList {

    Node head;
    Node tail;
    int size;

    DoublyLinkedList() {
      head = new Node(-1, -1);
      tail = new Node(-1, -1);

      head.next = tail;
      tail.prev = head;
      size = 0;
    }

    void addToFront(Node node) {

      node.next = head.next;
      node.prev = head;

      head.next.prev = node;
      head.next = node;

      size++;
    }

    void remove(Node node) {

      node.prev.next = node.next;
      node.next.prev = node.prev;

      size--;
    }

    Node removeLast() {

      if (size == 0) {
        return null;
      }

      Node last = tail.prev;
      remove(last);
      return last;
    }

    // **This code if we need MRU cache**
    Node removeFirst() {

      if (size == 0)
        return null;

      Node first = head.next;
      remove(first);
      return first;
    }

    boolean isEmpty() {
      return size == 0;
    }
  }

  public LFUCache(int capacity) {

    this.capacity = capacity;

    keyToNode = new HashMap<>();
    freqToList = new HashMap<>();

    minFrequency = 0;
  }

  // if node present, update the frequency ( remove from old list ,increase frequency, add to the new list ), return the value
  public int get(int key) {

    if (!keyToNode.containsKey(key)) {
      return -1;
    }

    Node node = keyToNode.get(key);

    updateFrequency(node);

    return node.value;
  }

  public void put(int key, int value) {

    if (capacity == 0) {
      return;
    }

    // Key already exists
    if (keyToNode.containsKey(key)) {

      Node node = keyToNode.get(key);

      node.value = value;

      updateFrequency(node);

      return;
    }

    // Cache full
    if (keyToNode.size() == capacity) {

      DoublyLinkedList list = freqToList.get(minFrequency);

      Node nodeToRemove = list.removeLast(); // IF MRU THEN list.removeFirst()

      keyToNode.remove(nodeToRemove.key);
    }

    Node newNode = new Node(key, value);

    minFrequency = 1;

    DoublyLinkedList list =
        freqToList.computeIfAbsent(1, k -> new DoublyLinkedList());

    list.addToFront(newNode);

    keyToNode.put(key, newNode);
  }

  // remove from old list ,increase frequency, add to the new list
  private void updateFrequency(Node node) {

    int oldFrequency = node.frequency;

    DoublyLinkedList oldList = freqToList.get(oldFrequency);

    oldList.remove(node);

    if (oldFrequency == minFrequency && oldList.isEmpty()) { // if it's from List which have minFrequency and list is empty
      minFrequency++;
    }

    node.frequency++; // actual frequency update

    DoublyLinkedList newList =
        freqToList.computeIfAbsent(node.frequency,
            k -> new DoublyLinkedList());

    newList.addToFront(node);
  }
}

//class Main {
//
//  public static void main(String[] args) {
//
//    String[] operations = {
//        "LFUCache",
//        "put",
//        "put",
//        "get",
//        "put",
//        "get",
//        "get",
//        "put",
//        "get",
//        "get",
//        "get"
//    };
//
//    int[][] arguments = {
//        {2},
//        {1, 1},
//        {2, 2},
//        {1},
//        {3, 3},
//        {2},
//        {3},
//        {4, 4},
//        {1},
//        {3},
//        {4}
//    };
//
//    LFUCache cache = null;
//
//    for (int i = 0; i < operations.length; i++) {
//
//      switch (operations[i]) {
//
//        case "LFUCache":
//          cache = new LFUCache(arguments[i][0]);
//          System.out.print("null ");
//          break;
//
//        case "put":
//          cache.put(arguments[i][0], arguments[i][1]);
//          System.out.print("null ");
//          break;
//
//        case "get":
//          int ans = cache.get(arguments[i][0]);
//          System.out.print(ans + " ");
//          break;
//      }
//    }
//  }
//}

class Main {

  public static void main(String[] args) {

    LFUCache cache = new LFUCache(2);

    System.out.println("put(1, 1) = " + "null");

    System.out.println("put(2, 2) = " + "null");
    cache.put(2, 2);

    System.out.println("get(1) = " + cache.get(1)); // 1

    System.out.println("put(3, 3) = " + "null");
    cache.put(3, 3);

    System.out.println("get(2) = " + cache.get(2)); // -1

    System.out.println("get(3) = " + cache.get(3)); // 3

    System.out.println("put(4, 4) = " + "null");
    cache.put(4, 4);

    System.out.println("get(1) = " + cache.get(1)); // -1

    System.out.println("get(3) = " + cache.get(3)); // 3

    System.out.println("get(4) = " + cache.get(4)); // 4
  }
}
