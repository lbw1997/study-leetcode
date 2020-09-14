package com.project.jvm.concurrent.chaptor07;

import com.project.jvm.concurrent.chaptor08.ProductInfo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.project.jvm.concurrent.chaptor08.ProductInfo.loadProductInfo;

public class Preloader {

    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }
    });

    private final Thread thread = new Thread(future);
    public void start() {thread.start();}
    public ProductInfo get() throws Throwable {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Exception) {
                throw cause;
            }else {
                throw launderThrowable(cause);
            }
        }
    }

    private RuntimeException launderThrowable(Throwable cause) {

        if (cause instanceof RuntimeException) {
            return (RuntimeException)cause;
        }else if (cause instanceof InternalError) {
            throw (Error)cause;
        }else
            throw new IllegalArgumentException("Not Unchecked",cause);
    }
}

