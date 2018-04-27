package transaction;

import java.util.List;

public interface Transaction {

    public void begin(String txn, List<String> args);

    public void rollback();

    public void cancel();

    public boolean isDone();

    public String getResponse();
}
