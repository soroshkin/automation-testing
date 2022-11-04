package com.epam.microservices.automation.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class SaveSongResponse {

  private final Integer id;

  @JsonCreator
  public SaveSongResponse(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  @Override
  public String toString() {
    return "SaveSongResponse{" +
      "id=" + id +
      '}';
  }
}