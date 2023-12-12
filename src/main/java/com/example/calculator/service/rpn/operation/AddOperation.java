package com.example.calculator.service.rpn.operation;


import com.example.calculator.service.rpn.strategy.RPNCalculationBinaryStrategy;

public class AddOperation implements RPNCalculationBinaryStrategy {
    @Override
    public double calculate(double first, double second) {
        return second + first;
    }
}
