package com.example.calculator.validators;

import com.example.calculator.service.rpn.exception.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public interface ExpressionValidator {

  void validate(String input) throws InvalidInputException;

}
