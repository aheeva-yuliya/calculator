package com.example.calculator.validators;

import com.example.calculator.service.rpn.exception.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class NumberExpressionValidator {

  public boolean isValid(final String number) throws InvalidInputException {
    try {
      Double.parseDouble(number);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
