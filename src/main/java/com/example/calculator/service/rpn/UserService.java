package com.example.calculator.service.rpn;

import com.example.calculator.model.Plan;
import com.example.calculator.model.User;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private Map<String,User> users = new HashMap<>();

  public UserService() {
    users.put("1", new User("1", "User1", Plan.TRIAL));
    users.put("2", new User("2", "User2", Plan.TRIAL));
  }

  public User getUser(String id) {
    return users.get(id);
  }

  public User updateUser(String id, String plan) {
    users.get(id).setPlan(Plan.valueOf(plan));
    return users.get(id);
  }
}
