package com.homdirect.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private Date time;
    private String status;
    private String content;
    private byte type;

    public class TransactionType {
        public static final byte DEPOSIT = 1;
        public static final byte WITHDRAW = 2;
        public static final byte TRANSFER = 3;
    }

    public static class Constant {
        public static final String STATUS_SUCCESS = "THANH CONG";
        public static final String STATUS_FAIL = "THAT BAI";
        public static final String CONTENT_DEPOSIT = "GUI TIEN";
        public static final String CONTENT_WITHDRAW = "RUT TIEN";
        public static final Double DEFAULT_BALANCE = 50000D;
        public static final Double MAX_AMOUNT_WITHDRAW = 10000000D;
        public static final int PAGE_SIZE = 10;
        public static final int FEE_TRANSFER = 3000;
    }

    public Transaction(String fromAccount, String toAccount, Double amount, Date time, String status, String content, byte type) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.time = time;
        this.status = status;
        this.content = content;
        this.type = type;
    }

    public Transaction() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }
}
