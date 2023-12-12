package com.example.calculator.service.rpn.operation;

import com.example.calculator.service.rpn.strategy.RPNCalculationBinaryStrategy;

public class PowerOperation implements RPNCalculationBinaryStrategy {
    public double calculate(double exponent, double base) {
        return Math.pow(base, exponent);
    }
}
