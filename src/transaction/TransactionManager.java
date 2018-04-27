package transaction;

import tuts.common.LoopTaskB;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TransactionManager implements Transaction   {

    private List<String> args = new ArrayList<>();
    private ExecutorService executorService;
    private Future<String> future;
    private static TransactionManager ourInstance = new TransactionManager();

    public static TransactionManager getInstance() {
        return ourInstance;
    }

    private TransactionManager() {}

    @Override
    public void begin(String txn, List<String> args) {
        executorService =  Executors.newSingleThreadExecutor();
//        executorService =  Executors.newFixedThreadPool(3);
         future =  executorService.submit(new TransactionWorker(txn,this.args));

         }

    @Override
    public void rollback() {
        this.cancel();
        this.begin("rollback",args);

    }

    @Override
    public void cancel() {
        this.future.cancel(true);
        executorService.shutdown();
    }

    @Override
    public boolean isDone() {
        return  this.future.isDone();
    }

    @Override
    public String getResponse() {
         try {
                executorService.shutdown();
                return AES.decrypt(future.get(30, TimeUnit.SECONDS));
            } catch (Exception e) {
             System.out.println(e);
             this.future.cancel(true);
                return null;
            }
    }
}
