package transaction;

import java.util.*;
import java.util.concurrent.*;

public class TransactionManager implements Transaction {

    private ExecutorService executorService;
    private List<Future<String>> responses;
    private List<TransactionWorker> works = new ArrayList<>();
    private static TransactionManager instance;

    synchronized public static TransactionManager getInstance() {
        synchronized (TransactionManager.class) {
            if (instance == null) instance = new TransactionManager();
            return instance;
        }
    }

    private TransactionManager() {
    }

    @Override
    public TransactionManager begin(List<String> requests) throws InterruptedException {
        System.out.println("length " + requests.size());
        executorService = Executors.newFixedThreadPool(requests.size());
        requests.forEach(request ->
                works.add(new TransactionWorker(request)));
        return this;
    }

    @Override
    public synchronized void rollback() {
    }

    @Override
    public synchronized void cancel() {
        this.works.clear();
        executorService.shutdown();
    }

    @Override
    public synchronized boolean isDone() {
        System.out.println("works size " + works.size());
        return false;
    }

    @Override
    public synchronized List<Future<String>> getResponse() {
        try {
            responses = executorService.invokeAll(works, 30, TimeUnit.SECONDS);
            this.cancel();
            return responses;
        } catch (Exception e) {
            return null;
        }
    }
}
