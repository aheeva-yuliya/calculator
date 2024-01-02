package com.example.calculator.validators;

import com.example.calculator.service.rpn.exception.InvalidInputException;


public class RpnExpressionValidator implements ExpressionValidator {

  @Override
  public void validate(final String input) throws InvalidInputException {
    if (input == null || input.isBlank()) {
      throw new InvalidInputException("Empty expressions are not allowed.");
    }

    if (input.length() > 1 ) {
      if (!input.contains(",")) {
        throw new InvalidInputException("Expressions should be separated by comma.");
      }
    }
  }
}
