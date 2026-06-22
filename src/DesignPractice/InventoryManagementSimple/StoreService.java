package DesignPractice.InventoryManagementSimple;

import java.util.HashMap;
import java.util.Map;

class StoreService {

  Map<String, Store> storeMap = new HashMap<>();

  void addStore(Store store) {
    if (storeMap.containsKey(store.storeName)) {
      storeMap.put(store.storeName, store);
    }
  }

  Store getStore(String name) {
    return storeMap.get(name);
  }
}
