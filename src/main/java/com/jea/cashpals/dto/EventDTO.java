package com.jea.cashpals.dto;

import java.util.List;

public class EventDTO {

  private String name;
  private String description;
  private float price;
  private int creatorId;
  private List<Integer> usersIds;
  private int partyId;
  private List<Integer> transactionIds;

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

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
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

  public List<Integer> getTransactionIds() {
    return transactionIds;
  }

  public void setTransactionIds(List<Integer> transactionIds) {
    this.transactionIds = transactionIds;
  }
}
