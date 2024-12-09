package com.jea.cashpals.dto;

import java.util.List;

public class TransactionDTO {

    private String name;
    private String description;
    private int debtorId;
    private List<Integer> indebtedId;
    private float value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(int debtorId) {
        this.debtorId = debtorId;
    }

    public List<Integer> getIndebtedId() {
        return indebtedId;
    }

    public void setIndebtedId(List<Integer> indebtedId) {
        this.indebtedId = indebtedId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
