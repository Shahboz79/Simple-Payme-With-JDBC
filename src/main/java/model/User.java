package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;

@NoArgsConstructor

@Setter
@Getter
public class User  implements Base{
    private Integer id;
    private String name;
    private String surname;
    private String username;
    private String telNumber;
    private String password;

    public User(String name, String surname, String username, String telNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.telNumber = telNumber;
        this.password = password;
    }

    @Override
    public void get(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.name = resultSet.getString("name");
            this.surname= resultSet.getString("surname");
            this.username = resultSet.getString("username");
            this.telNumber = resultSet.getString("tel_number");
            this.password = resultSet.getString("password");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
