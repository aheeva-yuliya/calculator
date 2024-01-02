package com.example.calculator.service.rpn.operation;

import com.example.calculator.service.rpn.strategy.RPNCalculationStrategy;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class OperationFactory {
  public static final String UNSUPPORTED_OPERATOR = "Unsupported operator: ";
  private final Map<String, RPNCalculationStrategy> strategyMap = new HashMap<>();

  public OperationFactory() {
    strategyMap.put("+", new AddOperation());
    strategyMap.put("*", new MultiplyOperation());
    strategyMap.put("-", new SubtractionOperation());
    strategyMap.put("/", new DivisionOperation());
    strategyMap.put("%", new ModuloOperation());
    strategyMap.put("^", new PowerOperation());
    strategyMap.put("!", new FactorialOperation());
  }

  public RPNCalculationStrategy getOperation(String operator) {
    if (!strategyMap.containsKey(operator)){
      throw new IllegalArgumentException(UNSUPPORTED_OPERATOR + operator);
    }
    return strategyMap.get(operator);
  }
}
