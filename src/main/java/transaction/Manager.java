package transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Manager {

    private HashMap <Integer,String> transactions = new HashMap<>(10);

    private HashMap <Integer,TransactionWorker> transactionWorkers = new HashMap<>(10);

   private HashMap <Integer,Future <String>>  futureMap = new HashMap<>(10);

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private static Manager ourInstance = new Manager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private Manager() {
    }


    public Manager addTransaction(Integer index, String transaction){
    if (this.transactions.size() == 10) return this;
        this.transactions.put(index,transaction);
        this.transactionWorkers.put(index,new TransactionWorker(transaction));
        return this;
    }

    public Manager start(Integer index){

        Thread thread = new Thread(()->{

            Future <String> future = futureMap.put(index,executorService.submit(transactionWorkers.get(index)));
            futureMap.put(index,future);
        });

        thread.start();

        return this;
    }

    public boolean isDone(Integer index) {
        if(!futureMap.containsKey(index)) {
            return false;
        } else {
            return true;
        }
        }

        public String getResponse(Integer index) {
            try {
                 return futureMap.get(index).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }




}
