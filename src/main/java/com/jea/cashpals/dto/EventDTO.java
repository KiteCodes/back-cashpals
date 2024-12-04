package com.jea.cashpals.dto;

public class EventDTO {

  private String name;
  private String description;
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

  public int getPartyId() {
    return partyId;
  }

  public void setPartyId(int groupId) {
    this.partyId = groupId;
  }
}
