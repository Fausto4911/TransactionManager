package transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class Manager {

    private HashMap<TxnType, Future<String>> transactions = new HashMap<>(10);
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static Manager ourInstance = new Manager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private Manager() {
    }

    public Manager addTransaction(TxnType txn, String request) {
        Future<String> future = executorService.submit(new TransactionWork(request));
        transactions.put(txn, future);
        return this;
    }

    public boolean isDone(TxnType index) {
        return transactions.get(index).isDone();
    }

    public String getResponse(TxnType txn) throws CancellationException, ExecutionException, TimeoutException, InterruptedException {
        String response = transactions.get(txn).get(30, TimeUnit.SECONDS);
        transactions.remove(txn);
        return response;
    }

    public void cancel(TxnType txn) {
        transactions.get(txn).cancel(true);
    }

    public boolean isCancel(TxnType txn) {
        return transactions.get(txn).isCancelled();
    }

    public List<Future<String>> getResponses(List<String> requests) throws InterruptedException {
        List<TransactionWork> transactionWorkers = new ArrayList<>();
        requests.forEach((request) -> transactionWorkers.add(new TransactionWork(request)));
        return executorService.invokeAll(transactionWorkers, 30, TimeUnit.SECONDS);
    }

}
