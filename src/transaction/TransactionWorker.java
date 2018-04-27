package transaction;

import core.Connection;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TransactionWorker implements Callable<String> {

    private String txn = "";
    private String response = null;
    public TransactionWorker(String txn, List<String> args){
        this.txn = txn;

    }

    @Override
    public String call() throws Exception {
        try {
//           System.out.println(transaction.AES.decrypt(Connection.getInstance().open().send(transaction.AES.encrypt(request)).getResponse()));
          response = Connection.getInstance().open().send(txn).getResponse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;

//        System.out.println("######## START TASK "+txn + "########");
//        for (int i = 10; i > 0 ; i--) {
//            System.out.println(" > TICK TICK " + i);
//        }
//        try {
//            TimeUnit.SECONDS.sleep( 5);
//        } catch (InterruptedException e) {
//            return "sleep exeption";
//        }
//        System.out.println("######## END TASK "+txn + "########");
//        return "Transaction Work Done"+" " + this.txn;
    }
}
