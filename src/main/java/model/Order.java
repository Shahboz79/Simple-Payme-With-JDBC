package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ResultSet;
import java.util.Date;
@NoArgsConstructor

@Setter
@Getter
public class Order implements Base {
    private Integer id;
    private Integer fromCardId;
    private Integer toCardId;
    private Integer amount;
    private Date sendAt;

    public Order(Integer fromCardId, Integer toCardId, Integer amount, Date sendAt) {
        this.fromCardId = fromCardId;
        this.toCardId = toCardId;
        this.amount = amount;
        this.sendAt = sendAt;
    }

    @Override
    public void get(ResultSet resultSet) {
        try {
            this.id = resultSet.getInt("id");
            this.fromCardId = resultSet.getInt("from_card_id");
            this.toCardId = resultSet.getInt("to_card_id");
            this.amount = resultSet.getInt("amount");
            this.sendAt = resultSet.getDate("send_at");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
