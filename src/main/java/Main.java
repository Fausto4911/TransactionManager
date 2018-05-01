import javafx.concurrent.Task;
import transaction.AES;
import transaction.Transaction;
import transaction.TransactionManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
//        Enviando => 1^4321^4321^1111^861299031389642^1^861299031389642^1.0^1^
//        3^94D3320A548471EC988CB62FA25EB7C0E4DA21D617BCA4805113D62B76484E60^4321^1111^
//        2^94D3320A548471EC988CB62FA25EB7C0E4DA21D617BCA4805113D62B76484E60^4321^1111^
//        4^94D3320A548471EC988CB62FA25EB7C0E4DA21D617BCA4805113D62B76484E60^4321^1111^


        List<String> request = new ArrayList<>();
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        request.add(AES.encrypt("8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^"));
        System.out.println("INICIO ");

//        try {
//            TransactionManager.getInstance().begin(request);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("is done " + TransactionManager.getInstance().isDone());

              Thread thread = new Thread(() ->{
          try {
              TransactionManager.getInstance().begin(request);
//              TransactionManager.getInstance().begin(request)
//                      .getResponse().forEach(x -> {
//                  try {
//                      System.out.println(AES.decrypt(x.get()));
//                  } catch (InterruptedException | ExecutionException e) {
//                      e.printStackTrace();
//                  }
//              });
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      });
        thread.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("#############################################");
        System.out.println("FIN" + "is done " + TransactionManager.getInstance().isDone());



    }

}

