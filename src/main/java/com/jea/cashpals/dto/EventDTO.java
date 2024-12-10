package com.jea.cashpals.dto;

import java.util.List;

public class EventDTO {

  private String name;
  private String description;
  private Float price;
  private Integer creator;
  private List<Integer> usersIds;
  private int partyId;

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

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Integer getCreator() {
    return creator;
  }

  public void setCreator(Integer creator) {
    this.creator = creator;
  }

  public List<Integer> getUsersIds() {
    return usersIds;
  }

  public void setUsersIds(List<Integer> usersIds) {
    this.usersIds = usersIds;
  }

  public int getPartyId() {
    return partyId;
  }

  public void setPartyId(int partyId) {
    this.partyId = partyId;
  }
}
