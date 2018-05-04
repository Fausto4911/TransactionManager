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
        return transactions.get(index).get(30, TimeUnit.SECONDS);
    }

    public void cancel(Integer index) {
        transactions.get(index).cancel(true);
    }

    public boolean isCancel(Integer index) {
        return transactions.get(index).isCancelled();
    }




//    public synchronized String getResponse(Integer index) {
//        try {
//            return futureMap.get(index).get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public Manager start(Integer index){
//
//        Thread thread = new Thread(()->{
//
//            Future <String> future = futureMap.put(index,executorService.submit(transactionWorkers.get(index)));
//            futureMap.put(index,future);
//        });
//
//        thread.start();
//
//        return this;
//    }


}
