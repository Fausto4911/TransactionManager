package tuts.common;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class LoopTaskB implements Callable<String> {

    private  String name = "";

    public LoopTaskB(String name){
        this.name = name;


    }
    @Override
    public String call() throws Exception {
        System.out.println("######## START TASK "+ name+" ######");
        for (int i = 10; i > 0 ; i--) {
            System.out.println("< "+name+ " > TICK TICK " + i);
            try {
                TimeUnit.MILLISECONDS.sleep((long) Math.random() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("######## END TASK "+ name+" ######");
    return "ok";
    }
}
