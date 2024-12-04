package com.jea.cashpals.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToOne
    private Event event;

    @JsonIgnore
    @OneToMany(mappedBy = "balance")
    private List<Transaction> transactiontList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<Transaction> getTransactionList() {
        return transactiontList;
    }
    public void setTransactionList(List<Transaction> transactiontList) {
        this.transactiontList = transactiontList;
    }

    public List<Transaction> getTransactiontList() {
        return transactiontList;
    }

    public void setTransactiontList(List<Transaction> transactiontList) {
        this.transactiontList = transactiontList;
    }
}
