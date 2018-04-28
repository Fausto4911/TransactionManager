import transaction.AES;
import transaction.Transaction;
import transaction.TransactionManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

//        transaction.MultipleThread multipleThread = new transaction.MultipleThread();
////        System.out.println(multipleThread.start());
////        System.out.println("Hola Putas");
//
//        try {
//            multipleThread.invokeAny();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Hola Putas");

public class Main {

    public static void main(String[] args) {

        List<String> request = new ArrayList<>();
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        try {
            TransactionManager.getInstance().begin(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        TransactionManager.getInstance().cancel();

        System.out.println("response -> "+TransactionManager.getInstance().getResponse());
        TransactionManager.getInstance().getResponse().forEach(x -> {
            try {
             System.out.println(AES.decrypt(x.get()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

//        TransactionManager.getInstance().begin("buy",ar);
////        System.out.println("is done? -> "+TransactionManager.getInstance().isDone());
////        TransactionManager.getInstance().cancel();
//        System.out.println("response -> "+TransactionManager.getInstance().getResponse());
    }

}
