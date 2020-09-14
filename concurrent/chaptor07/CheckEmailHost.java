package com.project.jvm.concurrent.chaptor07;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CheckEmailHost {

    public void checkHost(Set<String> hosts, long timeout, TimeUnit unit) throws InterruptedException {
        ExecutorService checkHostService = Executors.newCachedThreadPool();

        for (String host:hosts) {
            checkHostService.execute(new Runnable() {
                @Override
                public void run() {
                    if (checkMail(host)) {
                        //hasNewMail.set(true);
                    }
                }
            });
        }
        checkHostService.shutdown();
        checkHostService.awaitTermination(timeout,unit);
    }

    private boolean checkMail(String host) {
        return true;
    }
}
