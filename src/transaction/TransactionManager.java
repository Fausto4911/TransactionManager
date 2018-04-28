package transaction;

import java.util.*;
import java.util.concurrent.*;

public class TransactionManager implements Transaction   {

    private List<String> args = new ArrayList<>();
    private ExecutorService executorService;
    private List<Future<String>> responses;
    private Set<TransactionWorker> works;
    private static TransactionManager ourInstance = new TransactionManager();

    public static TransactionManager getInstance() {
        return ourInstance;
    }

    private TransactionManager() {}

    @Override
    public void begin(List<String>  requests) throws InterruptedException {
        System.out.println("length "+requests.size());
        executorService =  Executors.newFixedThreadPool(requests.size());
         works = new HashSet<>(requests.size());
         requests.forEach( request -> works.add(new TransactionWorker(request)) );

        //         future =  executorService.submit(new TransactionWorker(txn,this.args));

         }

    @Override
    public void rollback() {
        this.cancel();

    }

    @Override
    public void cancel() {
        this.responses.clear();
        executorService.shutdown();
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public List<Future<String>> getResponse() {
         try {
//                executorService.shutdown();
               return responses = executorService.invokeAll(works,30, TimeUnit.SECONDS);
//                return AES.decrypt(future.get(30, TimeUnit.SECONDS));
            } catch (Exception e) {
             System.out.println(e);
//             this.responses.clear();
                return null;
            }
    }
}
