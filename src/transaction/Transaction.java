package transaction;

import java.util.List;
import java.util.concurrent.Future;

public interface Transaction {

    void begin(List<String> requests) throws InterruptedException;

    void rollback();

    void cancel();

    boolean isDone();

    List<Future<String>> getResponse();
}
