package transaction;

import java.util.List;
import java.util.concurrent.Future;

public interface Transaction {

    public void begin(List<String>  requests) throws InterruptedException;

    public void rollback();

    public void cancel();

    public boolean isDone();

    public List<Future<String>> getResponse();
}
