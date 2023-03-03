package sg.edu.nus.iss.app.paf_day30assessment.models;

import java.io.Serializable;

public class Account implements Serializable {
    private String account_id;
    private String name;
    private float balance;

    public Account() {
    }

    public Account(String account_id, String name, float balance) {
        this.account_id = account_id;
        this.name = name;
        this.balance = balance;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [account_id=" + account_id + ", name=" + name + ", balance=" + balance + "]";
    }

}
