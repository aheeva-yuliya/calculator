package com.example.calculator.service.rpn.operation;

import com.example.calculator.service.rpn.strategy.RPNCalculationBinaryStrategy;

public class ModuloOperation implements RPNCalculationBinaryStrategy {
    @Override
    public double calculate(double first, double second) {
        return second % first;
    }
}
