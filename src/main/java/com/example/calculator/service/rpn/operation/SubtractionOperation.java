package com.example.calculator.service.rpn.operation;

import com.example.calculator.service.rpn.strategy.RPNCalculationBinaryStrategy;

public class SubtractionOperation implements RPNCalculationBinaryStrategy {

  @Override
  public double calculate(final double first, final double second) {
    return second - first;
  }
}
