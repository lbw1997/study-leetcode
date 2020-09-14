package com.project.jvm.concurrent.chaptor08;

import java.math.BigInteger;
import java.util.concurrent.*;

interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String,BigInteger>{

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}

public class Memoizer1<A,V> implements Computable<A,V>{
    private final ConcurrentHashMap<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;
    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(arg);
            if (future == null) {
                Callable<V> callable = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<>(callable);
                future = cache.putIfAbsent(arg,ft);
                if (future == null) {
                    ft.run();
                }
            }
            try {
                return future.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch(CancellationException e) {
                cache.remove(arg,future);
            }
        }
    }
}
