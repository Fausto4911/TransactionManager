package EfficientThreads;

import java.util.concurrent.TimeUnit;

public class FirstWay {

    public static void main(String [] args) {

        System.out.println("start thread");
        new FirstTask("1");
       Thread t = new FirstTask("2");
        System.out.println("end thread");
    }
}

class FirstTask extends Thread {

    private  String name = "";

    public FirstTask(String name){
        this.name = name;
        this.start();
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
