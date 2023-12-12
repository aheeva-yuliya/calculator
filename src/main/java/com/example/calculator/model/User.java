package com.example.calculator.model;

public class User {
  private String id;
  private String name;

  private Plan plan;

  public User(final String id) {
    this.id = id;
  }

  public User(final String id, final String name, final Plan plan) {
    this.id = id;
    this.name = name;
    this.plan = plan;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Plan getPlan() {
    return plan;
  }

  public void setPlan(final Plan plan) {
    this.plan = plan;
  }
}
