package sg.edu.nus.iss.app.paf_day30assessment.models;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction implements Serializable {
    private String transactionId;
    private String date;
    private String from_account;
    private String to_account;
    private float amount;

    public static JsonObject toJson(Transaction transaction) {
        return Json.createObjectBuilder().add("transactionId", transaction.getTransactionId())
                .add("date", transaction.getDate())
                .add("from_account", transaction.getFrom_account())
                .add("to_account", transaction.getTo_account())
                .add("amount", transaction.getAmount()).build();
    }
}
