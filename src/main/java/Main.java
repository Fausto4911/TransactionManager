import transaction.AES;
import transaction.Manager;
import transaction.TxnType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class Main {

    public static void main(String[] args) {

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

        try {
            Manager.getInstance().getResponses(request).forEach((response) -> {
                try {
                    System.out.println(response.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Manager.getInstance().addTransaction(TxnType.LOGIN,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");
        Manager.getInstance().addTransaction(TxnType.AWARD_PAYMENT,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");
        Manager.getInstance().addTransaction(TxnType.ACCOUNT,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");

        try {
            System.out.println( "1 -> "+Manager.getInstance().getResponse(TxnType.LOGIN) );
            System.out.println( "2 -> "+Manager.getInstance().getResponse(TxnType.AWARD_PAYMENT) );
            System.out.println( "3 -> "+Manager.getInstance().getResponse(TxnType.ACCOUNT) );
        } catch (ExecutionException e) {
            System.out.println("1");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("2");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("3");
            e.printStackTrace();
        } catch (CancellationException e) {
            System.out.println("4");
        }

        Manager.getInstance().addTransaction(TxnType.LOGIN,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");
        Manager.getInstance().addTransaction(TxnType.AWARD_PAYMENT,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");
        Manager.getInstance().addTransaction(TxnType.ACCOUNT,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");
        Manager.getInstance().addTransaction(TxnType.BUY_LOTTERY,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");
        Manager.getInstance().addTransaction(TxnType.DETAILS_BY_DRAW,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");
        Manager.getInstance().addTransaction(TxnType.ENTITY,"8^EAF159ADC5808466C55ACEFD49FCF473D3D75E4926B572F5321B0FA55C7752C4^4321^1111^");

        try {
            System.out.println( "1 -> "+Manager.getInstance().getResponse(TxnType.LOGIN) );
            System.out.println( "2 -> "+Manager.getInstance().getResponse(TxnType.AWARD_PAYMENT) );
            System.out.println( "3 -> "+Manager.getInstance().getResponse(TxnType.ACCOUNT) );
            System.out.println( "4 -> "+Manager.getInstance().getResponse(TxnType.ENTITY.BUY_LOTTERY) );
            System.out.println( "5 -> "+Manager.getInstance().getResponse(TxnType.DETAILS_BY_DRAW) );
            System.out.println( "6 -> "+Manager.getInstance().getResponse(TxnType.ENTITY) );
        } catch (ExecutionException e) {
            System.out.println("1");
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("2");
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("3");
            e.printStackTrace();
        } catch (CancellationException e) {
            System.out.println("4");
        }

        System.out.println("FIN");

    }

}

