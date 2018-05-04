package transaction;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.*;

public class Manager {

    private HashMap <Integer,Future<String>> transactions = new HashMap<>(10);

  private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static Manager ourInstance = new Manager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private Manager() {
    }

    public Manager addTransaction(Integer index, String request){
        Future<String> future =  executorService.submit(new TransactionWorker(request));
        transactions.put(index,future);
        return this;
    }


    public boolean isDone(Integer index) {
      return transactions.get(index).isDone();
    }

    public String getResponse(Integer index) throws CancellationException, ExecutionException, TimeoutException, InterruptedException {
        String response = transactions.get(index).get(30, TimeUnit.SECONDS);
                transactions.remove(index);
        return response;
    }

    public void cancel(Integer index) {
        transactions.get(index).cancel(true);
    }

    public boolean isCancel(Integer index) {
        return transactions.get(index).isCancelled();
    }


    public List<Future<String>> getResponses (List<String> requests) throws InterruptedException {
        List<TransactionWorker> transactionWorkers = new ArrayList<>();
    requests.forEach((request)->{
        transactionWorkers.add(new TransactionWorker(request));
    });
       return executorService.invokeAll(transactionWorkers, 30, TimeUnit.SECONDS);
    }

}
