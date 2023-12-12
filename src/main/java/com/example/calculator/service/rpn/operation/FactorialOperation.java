package com.example.calculator.service.rpn.operation;


import com.example.calculator.service.rpn.strategy.RPNCalculationUnaryStrategy;

public class FactorialOperation  implements RPNCalculationUnaryStrategy {
    @Override
    public double calculate(double value) {
        if (value < 0) {
            throw new RuntimeException("");
        }
        int result = 1;

        for (int i = 1; i <= value; i++) {
            result *= i;
        }

        return result;
    }
}
