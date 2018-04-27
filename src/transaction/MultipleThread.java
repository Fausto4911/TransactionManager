package transaction;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

public class MultipleThread {

    private Future <String> future;
    private  ExecutorService executorService;

    public MultipleThread() {

   }
    public String start() {
        executorService = Executors.newSingleThreadExecutor();
         future = executorService.submit(() -> {
             Thread.sleep(5500);
            System.out.println("Callable Task Executing...");
            return "String Object from Callable Task...";
        });

        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    return null;
    }

    public void invokeAny() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task 1";
        });
        callables.add(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task 2";
        });
        callables.add(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Task 3";
        });

        String result = executorService.invokeAny(callables);

        System.out.println("result = " + result);

        executorService.shutdown();
    }


}

