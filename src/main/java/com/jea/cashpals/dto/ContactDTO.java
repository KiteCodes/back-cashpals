package com.jea.cashpals.dto;

import java.util.List;

public class ContactDTO {

    private Integer id;
    private List<Integer> contactIDs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getContactIDs() {
        return contactIDs;
    }

    public void setContactIDs(List<Integer> contactIDs) {
        this.contactIDs = contactIDs;
    }
}
