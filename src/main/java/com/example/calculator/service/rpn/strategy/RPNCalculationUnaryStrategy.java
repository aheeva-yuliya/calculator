package com.example.calculator.service.rpn.strategy;

import com.example.calculator.service.rpn.CalculationType;

public interface RPNCalculationUnaryStrategy extends RPNCalculationStrategy {
    double calculate(double value);

    @Override
    default CalculationType getType() {
        return CalculationType.UNARY;
    }
}
