package controller;

import model.User;
import util.Util;

public class InHome {
    public static void home(User user) {
        int option = 0;
        do {

            System.out.println("\nPayme menu");
            System.out.println("1. Home");
            System.out.println("2. Plastic card add");
            System.out.println("3. Transfer");
            System.out.println("4. History");
            System.out.println("5. Balance qo'shish");

            System.out.print("yuqoridagilarni birini tanlang:");
            option = Util.SCANNER_INT.nextInt();

            switch (option) {
                case 1:
                    InHome.home(user);
                    break;
                case 2:
                    PlasticCardController.addCard(user);
                    break;
                case 3:
                    OrderController.transfer(user);
                    break;
                case 4:
                    OrderController.history(user);


            }

        } while (option == 0);

    }
}
