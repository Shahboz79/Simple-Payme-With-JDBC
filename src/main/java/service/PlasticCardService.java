package service;

import model.PlasticCard;
import model.PlasticCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlasticCardService implements Base<PlasticCard> {
    public static String addCard(Integer userId, String bank, String cardType, String number,
                                 Integer pinCode, Double balance) {
        List<PlasticCard> plasticcards = new PlasticCardService().getList();

        if (bank.isEmpty() || cardType.isEmpty() || number.isEmpty() || pinCode.equals("") || balance.equals("")) {
            return "one of the lines is the head";
        }


        for (PlasticCard plasticCard : plasticcards) {
            if (plasticCard.getNumber().equals(number)) {
                return "plastic number error entered";
            }

        }

        PlasticCard plasticCard = new PlasticCard(userId,bank,cardType,number,pinCode,balance);

        boolean add = new PlasticCardService().add(plasticCard);

        if (add) {
            return "plastic was successfully added";
        } else {
            return "error";
        }
    }

    @Override
    public boolean add(PlasticCard plasticCard) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection
                        .prepareStatement("insert into plastic_card( user_id, bank, card_type, number, pin_code, balance ) values (?,?,?,?,?,?)")
        ) {


            preparedStatement.setInt(1, plasticCard.getUserId());
            preparedStatement.setString(2, plasticCard.getBank());
            preparedStatement.setString(3, plasticCard.getCardType());
            preparedStatement.setString(4, plasticCard.getNumber());
            preparedStatement.setInt(5, plasticCard.getPinCode());
            preparedStatement.setDouble(6, plasticCard.getBalance());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PlasticCard> getList() {
        List<PlasticCard> plasticCardList = new ArrayList<>();
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {

            ResultSet resultSet
                    = statement.executeQuery("select * from plastic_card");
            while (resultSet.next()) {
                PlasticCard plasticCard = new PlasticCard();
                plasticCard.get(resultSet);
                plasticCardList.add(plasticCard);
            }
            return plasticCardList;

        } catch (Exception e) {
            return null;

        }
    }
    public boolean edit(PlasticCard card) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection
                        .prepareStatement("update plastic_card set balance = ? where id = ?")
        ) {

            preparedStatement.setDouble(1, card.getBalance());
            preparedStatement.setObject(2, card.getId());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
