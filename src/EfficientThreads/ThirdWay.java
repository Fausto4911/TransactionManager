package EfficientThreads;

import java.util.concurrent.TimeUnit;

public class ThirdWay {

        public static void main(String [] args) {

            System.out.println("start thread");
            new ThirdTask("1");
            new ThirdTask("2");
            System.out.println("end thread");
        }
    }

    class ThirdTask implements Runnable {

        private  String name = "";

        public ThirdTask(String name){
            this.name = name;
            new Thread(this).start();
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

