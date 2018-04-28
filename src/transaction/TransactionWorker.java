package transaction;

import core.Connection;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TransactionWorker implements Callable<String> {

    private String request = "";
    private String response = null;
    public TransactionWorker(String request ){
        this.request = request;

    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println("request "+request);
//           System.out.println(transaction.AES.decrypt(Connection.getInstance().open().send(transaction.AES.encrypt(request)).getResponse()));
//            Connection.getInstance().open();
            response = new Connection().open().send(request).getResponse();
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
