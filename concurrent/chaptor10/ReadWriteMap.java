package com.project.jvm.concurrent.chaptor10;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁对于Map集合的作用，对于一般并发需求，ConcurrentHashMap已经足够优秀
 * 对于LinkedHashMap这种可替换元素的Map有并发需求时，就可以提供读-写锁来提高吞吐量
 * 读-写允许多个读者并发访问被守护的对象，而对于写操作只能由得到锁的线程执行以及进行锁的释放
 * @param <K>
 * @param <V>
 */
public class ReadWriteMap<K,V> {

    private final Map<K,V> map;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock r = lock.readLock();
    private final Lock w = lock.writeLock();

    ReadWriteMap(Map<K,V> map) {
        this.map = map;
    }

    public V put(K key,V value) {
        w.lock();
        try {
            return map.put(key,value);
        } finally {
            w.unlock();
        }
    }

    public V get(K key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }
}
