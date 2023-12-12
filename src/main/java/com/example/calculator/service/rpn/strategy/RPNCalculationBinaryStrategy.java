package com.example.calculator.service.rpn.strategy;

import com.example.calculator.service.rpn.CalculationType;

public interface RPNCalculationBinaryStrategy extends RPNCalculationStrategy {
    double calculate(double first, double second);

    @Override
    default CalculationType getType() {
        return CalculationType.BINARY;
    }
}
