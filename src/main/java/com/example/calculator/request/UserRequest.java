package com.example.calculator.request;

public class UserRequest {
  private String plan;

  public UserRequest() {
  }

  public UserRequest(final String plan) {
    this.plan = plan;
  }

  public String getPlan() {
    return plan;
  }

  public void setPlan(final String plan) {
    this.plan = plan;
  }
}
