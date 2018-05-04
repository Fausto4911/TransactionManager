package transaction;

import core.Connection;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TransactionWorker implements Callable<String> {

    private String request = "";
    private String response = null;

    public TransactionWorker(String request) {
        this.request = request;

    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println("request " + request);
            response = new Connection().open().send(request).getResponse();
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
