package com.example.calculator.response;

import com.example.calculator.model.User;

public class UserResponse {
  private User user;

  public UserResponse(final User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(final User user) {
    this.user = user;
  }
}
