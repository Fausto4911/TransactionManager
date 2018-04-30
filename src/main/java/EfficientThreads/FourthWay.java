package EfficientThreads;

import java.util.concurrent.TimeUnit;

public class FourthWay {

    public static void main(String [] args) {

        System.out.println("start thread");
       new Thread( new FourthWayTask("1")).start();
       Thread t = new Thread(new FourthWayTask("2"));
       t.start();
       System.out.println("end thread");
    }
}

class FourthWayTask implements Runnable {

    private  String name = "";

    public FourthWayTask(String name){
        this.name = name;

    }

    @Override
    public void run() {
        for (int i = 10; i > 0 ; i--) {
            System.out.println("< "+name+ " > TICK TICK " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
