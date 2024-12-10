package com.jea.cashpals.entitiy;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Float value;

    @ManyToOne
    private User debtor;

    @ManyToOne
    private User indebted;

    @ManyToOne
    private Balance balance;

    @ManyToOne
    private Event event;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = Math.round(value * 100) / 100.0f;;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public User getDebtor() {
        return debtor;
    }

    public void setDebtor(User debtor) {
        this.debtor = debtor;
    }

    public User getIndebted() {
        return indebted;
    }

    public void setIndebted(User indebted) {
        this.indebted = indebted;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
