package controller;

import model.User;
import service.PlasticCardService;
import service.UserService;
import util.Util;

public class PlasticCardController {
    public static void addCard(User user) {

        Integer userId= user.getId();

        System.out.print("Enter bank name: ");
        String bank= Util.SCANNER_STR.nextLine().trim();

        System.out.print("Enter cardType: ");
        String cardType=Util.SCANNER_STR.nextLine().trim();

        System.out.print("Enter card number: ");
        String number= Util.SCANNER_STR.nextLine().trim();

        System.out.print("Enter pinCode: ");
        Integer pinCode= Util.SCANNER_INT.nextInt();

        System.out.print("Enter balance: ");
        Double balance= Util.SCANNER_Double.nextDouble();

        String addCard = PlasticCardService.addCard(userId,bank,cardType,number,pinCode,balance);

        System.out.println(addCard);


    }
}
