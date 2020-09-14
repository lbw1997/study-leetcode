package com.project.jvm.concurrent.chaptor07;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class WebCrawler {

    private volatile TrackingExecutor executor;
    private final Set<URL> urlToCrawler = new HashSet<>();
    private final long TIMEOUT = 1;
    private final TimeUnit UNIT = TimeUnit.SECONDS;

    public synchronized void start() {
        executor = new TrackingExecutor(Executors.newCachedThreadPool());
        for (URL url:urlToCrawler) {
            submitCrawlTask(url);
        }
        urlToCrawler.clear();
    }

    public synchronized void stop() {
        try {
            saveUncrawled(executor.shutdownNow());
            if (executor.awaitTermination(TIMEOUT,UNIT));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void submitCrawlTask(URL url) {
        executor.submit(new CrawlTask(url));
    }

    protected abstract List<URL> processPage(URL url);

    private void saveUncrawled(List<Runnable> uncrawled) {
        for (Runnable task:uncrawled) {
            urlToCrawler.add(((CrawlTask)task).getPage());
        }
    }

    private class CrawlTask implements Runnable {

        private URL url;

        CrawlTask(URL url) {
            this.url = url;
        }

        @Override
        public void run() {
            for (URL link:processPage(url)) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                submitCrawlTask(link);
            }
        }
        public URL getPage() {
            return url;
        }
    }
}
