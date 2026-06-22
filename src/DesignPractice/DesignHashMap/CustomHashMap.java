package DesignPractice.DesignHashMap;

public class CustomHashMap<K, V> {

  private static final int DEFAULT_CAPACITY = 16;

  // same as Java HashMap
  private static final int MAXIMUM_CAPACITY = 1 << 30;

  private static final float DEFAULT_LOAD_FACTOR = 0.75f;

  private Node<K, V>[] buckets;

  private int capacity;

  private int size;

  private float loadFactor;

  // resize threshold
  private int threshold;

  @SuppressWarnings("unchecked")
  public CustomHashMap() {

    this.capacity = DEFAULT_CAPACITY;
    this.loadFactor = DEFAULT_LOAD_FACTOR;

    this.threshold = (int) (capacity * loadFactor);

    this.buckets = new Node[capacity];
  }


  private int getBucketIndex(K key) {

    if (key == null)
      return 0;

    return (key.hashCode() & 0x7FFFFFFF) % capacity;
  }

  private boolean equalsKey(K k1, K k2) {

    if (k1 == null && k2 == null)
      return true;

    if (k1 == null || k2 == null)
      return false;

    return k1.equals(k2);
  }

  private void resize() {

    if (capacity == MAXIMUM_CAPACITY) {
      return;
    }

    int newCapacity = capacity * 2;

    if (newCapacity > MAXIMUM_CAPACITY) {
      newCapacity = MAXIMUM_CAPACITY;
    }

    Node<K, V>[] oldBuckets = buckets;

    buckets = new Node[newCapacity];

    capacity = newCapacity;

    threshold = (int) (capacity * loadFactor);

    rehash(oldBuckets);
  }

  private void rehash(Node<K, V>[] oldBuckets) {

    for (Node<K, V> head : oldBuckets) {

      Node<K, V> current = head;

      while (current != null) {

        Node<K, V> next = current.next;

        current.next = null;
        current.prev = null;

        int index = getBucketIndex(current.key);

        current.next = buckets[index];

        if (buckets[index] != null)
          buckets[index].prev = current;

        buckets[index] = current;

        current = next;
      }
    }
  }


  private Node<K, V> getNode(K key) {

    int index = getBucketIndex(key);

    Node<K, V> current = buckets[index];

    while (current != null) {

      if (equalsKey(current.key, key))
        return current;

      current = current.next;
    }

    return null;
  }

  public void put(K key, V value) {

    int index = getBucketIndex(key);

    Node<K, V> current = buckets[index];

    while (current != null) {

      if (equalsKey(current.key, key)) {
        current.value = value;
        return;
      }

      current = current.next;
    }

    Node<K, V> node = new Node<>(key, value);

    node.next = buckets[index];

    if (buckets[index] != null)
      buckets[index].prev = node;

    buckets[index] = node;

    size++;

    if (size > threshold) {
      resize();
    }
  }

  public V get(K key) {

    Node<K, V> node = getNode(key);

    return node == null ? null : node.value;
  }

  public V remove(K key) {

    Node<K, V> node = getNode(key);

    if (node == null)
      return null;

    int index = getBucketIndex(key);

    if (node.prev != null) {
      node.prev.next = node.next;
    } else {
      buckets[index] = node.next;
    }

    if (node.next != null)
      node.next.prev = node.prev;

    size--;

    return node.value;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean containsKey(K key) {

    return getNode(key) != null;
  }

}
