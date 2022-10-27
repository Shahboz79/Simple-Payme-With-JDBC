package controller;

import model.User;
import service.UserService;
import util.Util;

public class UserController {
    public static void login() {
        String username=null;
        String password=null;
        do {

            System.out.println("Username and password must be entered correctly");
            System.out.print("Enter username: ");
            username = Util.SCANNER_STR.nextLine();

            System.out.print("Enter password: ");
            password = Util.SCANNER_STR.nextLine();

            UserService userService = new UserService();
            User user = userService.getUser(username, password);
            InHome.home(user);
        }
        while (username.equals(null)||password.equals(null));
    }

    public static void register() {
        System.out.print("Enter name:");
        String name = Util.SCANNER_STR.nextLine().trim();
        System.out.print("Enter surname:");
        String surname = Util.SCANNER_STR.nextLine().trim();
        System.out.print("Enter username: ");
        String userName = Util.SCANNER_STR.nextLine().trim();
        System.out.print("Enter telNumber: ");
        String telNumber = Util.SCANNER_STR.nextLine().trim();
        System.out.print("Enter password: ");
        String passsword = Util.SCANNER_STR.nextLine().trim();

        String register = UserService.register(name,surname, userName, telNumber, passsword);

        System.out.println(register);
    }
}
