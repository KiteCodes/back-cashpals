package com.jea.cashpals.dto;

import java.util.List;

public class TransactionDTO {

    private int eventId;
    private int debtorId;
    private List<Integer> indebtedId;
    private float value;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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
