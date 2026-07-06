package main.DesignPractice.SearchAutoComplete;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

// Give me all sentences starting with this prefix -> trie,... The prefix tells you exactly where to start
// PriorityQueue ( min heap )

/**
 * Trie node implementation for storing sentences with their frequencies
 */
class Trie {
  // Array to store child nodes (26 letters + 1 space character)
  Trie[] children = new Trie[27];
  // Frequency count of the sentence ending at this node
  int frequency;
  // The complete sentence stored at this node
  String sentence = "";

  /**
   * Inserts a sentence into the trie with given frequency
   * @param sentence the sentence to insert
   * @param times the frequency/times to add for this sentence
   */
  void insert(String sentence, int times) {
    Trie currentNode = this; // this simply refers to the current Trie object on which insert() is called.

    // Traverse through each character in the sentence
    for (char ch : sentence.toCharArray()) {
      // Map character to index: space -> 26, 'a'-'z' -> 0-25
      int index = ch == ' ' ? 26 : ch - 'a';

      // Create new node if path doesn't exist
      if (currentNode.children[index] == null) {
        currentNode.children[index] = new Trie();
      }
      currentNode = currentNode.children[index];
    }

    // Update frequency and store sentence at leaf node
    currentNode.frequency += times;
    currentNode.sentence = sentence;
  }

  /**
   * Searches for a node with given prefix
   * @param prefix the prefix to search for
   * @return the trie node at the end of prefix path, or null if not found
   */
  Trie search(String prefix) {
    Trie currentNode = this;

    // Traverse the trie following the prefix path
    for (char ch : prefix.toCharArray()) {
      // Map character to index: space -> 26, 'a'-'z' -> 0-25
      int index = ch == ' ' ? 26 : ch - 'a';

      // Return null if prefix path doesn't exist
      if (currentNode.children[index] == null) {
        return null;
      }
      currentNode = currentNode.children[index];
    }

    return currentNode;
  }
}

/**
 * Autocomplete system that suggests top 3 sentences based on user input
 */
class AutocompleteSystem {
  // Root of the trie storing all sentences
  private Trie trieRoot = new Trie();
  // Buffer to store current user input
  private StringBuilder currentInput = new StringBuilder();

  /**
   * Constructor to initialize the autocomplete system
   * @param sentences array of initial sentences
   * @param times array of frequencies corresponding to each sentence
   */
  public AutocompleteSystem(String[] sentences, int[] times) {
    // Insert all initial sentences with their frequencies into the trie
    int index = 0;
    for (String sentence : sentences) {
      trieRoot.insert(sentence, times[index++]);
    }
  }

  /**
   * Processes user input character and returns autocomplete suggestions
   * @param c the input character
   * @return list of top 3 autocomplete suggestions, or empty list if c is '#'
   */
  public List<String> input(char c) {
    List<String> suggestions = new ArrayList<>();

    // Handle end of sentence input
    if (c == '#') {
      // Add the completed sentence to the trie with frequency 1
      trieRoot.insert(currentInput.toString(), 1);
      // Reset the input buffer for next sentence
      currentInput = new StringBuilder();
      return suggestions;
    }

    // Append character to current input
    currentInput.append(c);

    // Search for the prefix in the trie
    Trie prefixNode = trieRoot.search(currentInput.toString());
    if (prefixNode == null) {
      return suggestions;
    }

    // Use min heap to maintain top 3 sentences
    // Priority: lower frequency first, then reverse alphabetical order
    PriorityQueue<Trie> minHeap = new PriorityQueue<>((a, b) ->
        a.frequency == b.frequency ? b.sentence.compareTo(a.sentence) : a.frequency - b.frequency);

    // Find all sentences with the current prefix
    dfs(prefixNode, minHeap);

    // Extract results from heap in reverse order
    while (!minHeap.isEmpty()) {
      suggestions.add(0, minHeap.poll().sentence);
    }

    return suggestions;
  }

  /**
   * Depth-first search to find all sentences in the subtree
   * Maintains only top 3 sentences in the priority queue
   * @param node current trie node
   * @param minHeap priority queue to store top 3 sentences
   */
  private void dfs(Trie node, PriorityQueue<Trie> minHeap) {
    if (node == null) {
      return;
    }

    // If this node represents a complete sentence
    if (node.frequency > 0) {
      minHeap.offer(node);
      // Keep only top 3 sentences
      if (minHeap.size() > 3) {
        minHeap.poll();
      }
    }

    // Recursively search all children
    for (Trie child : node.children) {
      dfs(child, minHeap);
    }
  }
}

class Main {

  public static void main(String[] args) {

    String[] sentences = {
        "i love you",
        "island",
        "ironman",
        "i love leetcode"
    };

    int[] times = {5, 3, 2, 2};

    AutocompleteSystem system = new AutocompleteSystem(sentences, times);

    System.out.println(system.input('i'));   // ["i love you", "island", "i love leetcode"]

    System.out.println(system.input(' '));   // ["i love you", "i love leetcode"]

    System.out.println(system.input('a'));   // []

    System.out.println(system.input('#'));   // []
  }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
