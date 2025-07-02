package com.tienngv.security.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // accessOrder = true để duy trì thứ tự truy cập
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // Khi nào xóa phần tử cũ nhất?
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");

        System.out.println("Truy cập key 2: " + cache.get(2)); // key 2 trở nên mới nhất

        cache.put(4, "Four"); // Lúc này key 1 sẽ bị loại (ít được dùng nhất)
        cache.put(5, "Five"); // Lúc này key 3 sẽ bị loại (ít được dùng nhất)

        System.out.println("Cache sau khi thêm key 4:");
        for (Map.Entry<Integer, String> entry : cache.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}

