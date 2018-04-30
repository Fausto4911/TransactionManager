package EfficientThreads;

import java.util.concurrent.TimeUnit;

public class SecondWay {

    public static void main(String [] args) {

        System.out.println("start thread");
        new SecondTask("1").start();
        Thread t = new SecondTask("2");
        t.start();
        System.out.println("end thread");
    }
}

class SecondTask extends Thread {

    private  String name = "";

    public SecondTask(String name){
        this.name = name;
//        this.start();
//    super.start();
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