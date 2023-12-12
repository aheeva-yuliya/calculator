package com.example.calculator.controller;


import com.example.calculator.request.ExpressionRequest;
import com.example.calculator.response.ExpressionResponse;
import com.example.calculator.service.rpn.exception.InvalidInputException;
import com.example.calculator.service.rpn.RPNCalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/expression")
public class ExpressionController {
  private RPNCalculatorService service;

  public ExpressionController(RPNCalculatorService service) {
    this.service = service;
  }

  @GetMapping("")
  public String greeting(){
    return "Welcome to RPN Calculator";
  }

  @PostMapping("")
  public ExpressionResponse postExpression(@RequestBody ExpressionRequest body) {
      try {
        return new ExpressionResponse(service.calculate(body.getExpression()));
      } catch (InvalidInputException e) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST, e.getMessage(), e);
      }
  }
}
