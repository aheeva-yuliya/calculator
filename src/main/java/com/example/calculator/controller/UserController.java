package com.example.calculator.controller;


import com.example.calculator.request.UserRequest;
import com.example.calculator.response.UserResponse;
import com.example.calculator.service.rpn.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
  private UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable(name = "id") String id) {
    return new UserResponse(userService.getUser(id));
  }

  @PostMapping("/{id}")
  public UserResponse updateUser(@PathVariable String id, @RequestBody UserRequest body) {
    return new UserResponse(userService.updateUser(id, body.getPlan()));
  }
}
