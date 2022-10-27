package service;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService implements Base<User> {
    public static String register(String name, String surname, String userName, String telNumber, String passsword) {
        List<User> users = new UserService().getList();

        if (name.isEmpty() || userName.isEmpty() || telNumber.isEmpty() || passsword.isEmpty()) {
            return "one of the lines is the head";
        }

        for (User user : users) {
            if (user.getUsername().equals(userName)) {
                return "Such an username is registered";
            }

        }

        User user = new User(name,surname,userName,telNumber,passsword);

        boolean add = new UserService().add(user);

        if (add) {
            return "Successful registered";
        } else {
            return "Not successful registered";
        }
    }



    @Override
    public boolean add(User user) {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement
                        = connection
                        .prepareStatement("insert into users(name, surname, username, tel_number, password ) values (?,?,?,?,?)")
        ) {


            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getTelNumber());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List getList() {
        List<User> userList = new ArrayList<>();
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
        ) {

            ResultSet resultSet
                    = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.get(resultSet);
                userList.add(user);
            }
            return userList;

        } catch (Exception e) {
            return null;

        }
    }

    public User getUser(String usernames, String passwords) {

        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(
                    "select * from getUser('" + usernames + "','" + passwords + "')");

            while (resultSet.next()) {
                User user = new User();
                user.get(resultSet);

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
