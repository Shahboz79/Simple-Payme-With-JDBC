package controller;

import model.Order;
import model.PlasticCard;
import model.User;
import service.OrderService;
import service.PlasticCardService;
import service.UserService;
import util.Util;

import java.util.List;
import java.util.Optional;

public class OrderController {
    public static void transfer(User user) {

        List<PlasticCard> plasticCards = new PlasticCardService().getList();
        System.out.println("Your card number ");
        for (PlasticCard plasticCard : plasticCards) {
            if (plasticCard.getUserId().equals(user.getId())) {
                System.out.println("Card number: " + plasticCard.getNumber());
            }
        }

        System.out.println("Enter your card number: ");
        String yourCardNumber = Util.SCANNER_STR.nextLine().trim();

        System.out.println("Enter the card number to be sent:");
        String sentCardNumber = Util.SCANNER_STR.nextLine().trim();

        Optional<PlasticCard> any = plasticCards.stream().
                filter(plasticCard -> plasticCard.getNumber().equals(sentCardNumber)).findAny();
        if (!any.isPresent()) {
            System.out.println("the card number to be sent error");
            return;
        }
//        PlasticCard plasticCard1 = any.get();

        System.out.println("Enter amount");
        Integer amount = Util.SCANNER_INT.nextInt();

        PlasticCard fromCard = null;
        for (PlasticCard plasticCard : plasticCards) {
            if (plasticCard.getNumber().equals(yourCardNumber)) {
                fromCard = plasticCard;
            }
        }

        PlasticCard toCard = null;
        for (PlasticCard plasticCard : plasticCards) {
            if (plasticCard.getNumber().equals(sentCardNumber)) {
                toCard = plasticCard;
            }
        }
        Integer fromCardId = fromCard.getId();
        Integer toCardId = toCard.getId();

        if (amount > fromCard.getBalance()) {
            System.out.println("you don't have enough money");
            InHome.home(user);

        } else {

            String transfers = OrderService.transfer(fromCardId, toCardId, amount);

            System.out.println(transfers);

        }
    }


    public static void history(User user) {
        List<Order> orders = new OrderService().getList();
        List<PlasticCard> plasticcards = new PlasticCardService().getList();
        PlasticCard card = null;
        for (PlasticCard plasticcard : plasticcards) {
            if (plasticcard.getUserId().equals(user.getId())) {
                card = plasticcard;
            }
        }

        for (Order order : orders) {
            if (order.getFromCardId().equals(card.getId())) {
                System.out.println("transfers");
                System.out.println("order.getFromCardNumber = " + card.getNumber());
                Optional<PlasticCard> any = plasticcards.stream().
                        filter(plasticcard -> plasticcard.getId().equals(order.getToCardId())).
                        findAny();
                PlasticCard plasticCard1 = any.get();
                System.out.println("order.getToCardNumber = " + plasticCard1.getNumber());
                System.out.println("order.getAmount() = " + order.getAmount());
                System.out.println("order.getSendAt() = " + order.getSendAt());
                System.out.println();
            }

        }
        for (Order order : orders) {
            if (order.getToCardId().equals(card.getId())) {
                System.out.println("accepts");
                Optional<PlasticCard> any = plasticcards.stream().
                        filter(plasticcard -> plasticcard.getId().equals(order.getFromCardId())).findAny();
                PlasticCard plasticCard1 = any.get();
                System.out.println("order.getFromCardNumber = " + plasticCard1.getNumber());
                System.out.println("order.getToCardNumber = " + card.getNumber());
                System.out.println("order.getAmount() = " + order.getAmount());
                System.out.println("order.getSendAt() = " + order.getSendAt());
                System.out.println();
            }

        }
    }
}
