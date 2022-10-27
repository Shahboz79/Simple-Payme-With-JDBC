package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;

@NoArgsConstructor

@Setter
@Getter
public class PlasticCard implements Base{
    private Integer id;
    private Integer userId;
    private String bank;
    private String cardType;
    private String number; // 16 talik
    private Integer pinCode; // 4 talik pin kod
    private Double balance;

    public PlasticCard(Integer userId, String bank, String cardType, String number, Integer pinCode, Double balance) {
        this.userId = userId;
        this.bank = bank;
        this.cardType = cardType;
        this.number = number;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    @Override
    public void get(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.userId= resultSet.getInt("user_id");
            this.bank= resultSet.getString("bank");
            this.cardType = resultSet.getString("card_type");
            this.number = resultSet.getString("number");
            this.pinCode = resultSet.getInt("pin_code");
            this.balance= resultSet.getDouble("balance");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
