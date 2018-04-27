package executors;

import tuts.common.LoopTaskA;
import tuts.common.LoopTaskB;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FixedThreadPool   {

    public static void main(String [] args) {

        ExecutorService executorService =  Executors.newFixedThreadPool(3);
        System.out.println("start thread");

       Future<String> future =  executorService.submit(new LoopTaskB("callable 1"));
        System.out.println("end thread");

        future.cancel(true);
        future.isDone();

        try {
            System.out.println( "future.get() -> " +future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("really end thread");
//        executorService.execute(new LoopTaskA("2"));
//        executorService.execute(new LoopTaskA("3"));

        executorService.shutdown();



    }
}
