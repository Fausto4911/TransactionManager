package transaction;

import java.util.*;
import java.util.concurrent.*;

public class TransactionManager implements Transaction {

    private ExecutorService executorService;
    private List<Future<String>> responses;
    private Set<TransactionWork> works;
    private static TransactionManager ourInstance = new TransactionManager();

    public static TransactionManager getInstance() {
        return ourInstance;
    }

    private TransactionManager() {
    }

    @Override
    public TransactionManager begin(List<String> requests) throws InterruptedException {
        System.out.println("length " + requests.size());
        executorService = Executors.newFixedThreadPool(requests.size());
        works = new HashSet<>(requests.size());
        requests.forEach(request ->
                works.add(new TransactionWork(request)));
        return this;
    }

    @Override
    public void rollback() {
    }

    @Override
    public void cancel() {
        this.works.clear();
        executorService.shutdown();
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public List<Future<String>> getResponse() {
        try {
            responses = executorService.invokeAll(works, 30, TimeUnit.SECONDS);
            this.cancel();
            return responses;
        } catch (Exception e) {
            return null;
        }
    }
}
