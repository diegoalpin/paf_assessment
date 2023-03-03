package sg.edu.nus.iss.app.paf_day30assessment.models;

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

public class Transfer implements Serializable {
    private Account from;
    private Account to;

    @Positive(message = "Cannot transfer negative or zero")
    @Min(value = 10, message = "Min amount to transfer is 10")
    private float amount;
    private String comments;

    private String toId;
    private String fromId;

    public Transfer() {
    }

    public Transfer(Account from, Account to, float amount, String comments) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.comments = comments;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    @Override
    public String toString() {
        return "Transfer [from=" + from + ", to=" + to + ", amount=" + amount + ", comments=" + comments + ", toId="
                + toId + ", fromId=" + fromId + "]";
    }

}
