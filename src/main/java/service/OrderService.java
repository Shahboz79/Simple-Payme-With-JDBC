package service;

import controller.InHome;
import model.Order;
import model.Order;
import model.PlasticCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements Base<Order>{


    public static String transfer(Integer fromCardId, Integer toCardId, Integer amount) {
        List<PlasticCard> plasticCards = new PlasticCardService().getList();


        PlasticCard fromCard = null;
        for (PlasticCard plasticCard: plasticCards) {
            if (plasticCard.getId().equals(fromCardId)) {
                fromCard=plasticCard;
            }
        }
        PlasticCard toCard = null;
        for (PlasticCard plasticCard: plasticCards) {
            if (plasticCard.getId().equals(toCardId)) {
                toCard=plasticCard;
            }
        }
        fromCard.setBalance(fromCard.getBalance()-amount);
        toCard.setBalance(toCard.getBalance()+amount);

        new PlasticCardService().edit(fromCard);
        new PlasticCardService().edit(toCard);

       Order order= new Order(fromCardId,toCardId,amount,new java.util.Date());
        boolean add = new OrderService().add(order);

        if (add) {
            return "the transfer was successful";
        } else {
            return "error";
        }






    }

    @Override
    public boolean add(Order order) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection
                        .prepareStatement("insert into orders( from_card_id, to_card_id, amount, send_at ) values (?,?,?,?)")
        ) {


            preparedStatement.setInt(1, order.getFromCardId());
            preparedStatement.setInt(2, order.getToCardId());
            preparedStatement.setInt(3, order.getAmount());
            preparedStatement.setDate(4, new Date( order.getSendAt().getTime()));

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Order> getList() {
        List<Order> orders = new ArrayList<>();
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {

            ResultSet resultSet
                    = statement.executeQuery("select * from orders");
            while (resultSet.next()) {
                Order order = new Order();
                order.get(resultSet);
                orders.add(order);
            }
            return orders;

        } catch (Exception e) {
            return null;

        }
    }
}
