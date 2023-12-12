package com.example.calculator.service.rpn;

import com.example.calculator.service.rpn.CalculationType;
import com.example.calculator.service.rpn.exception.InvalidInputException;
import com.example.calculator.service.rpn.operation.AddOperation;
import com.example.calculator.service.rpn.operation.DivisionOperation;
import com.example.calculator.service.rpn.operation.FactorialOperation;
import com.example.calculator.service.rpn.operation.ModuloOperation;
import com.example.calculator.service.rpn.operation.MultiplyOperation;
import com.example.calculator.service.rpn.operation.PowerOperation;
import com.example.calculator.service.rpn.operation.SubtractionOperation;
import com.example.calculator.service.rpn.strategy.RPNCalculationBinaryStrategy;
import com.example.calculator.service.rpn.strategy.RPNCalculationStrategy;
import com.example.calculator.service.rpn.strategy.RPNCalculationUnaryStrategy;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class RPNCalculatorService {
  public static final String UNSUPPORTED_OPERATOR = "Unsupported operator: ";

  private final Map<String, RPNCalculationStrategy> strategyMap = new HashMap<>();
  private RPNCalculationStrategy strategy;

  public void setStrategy(RPNCalculationStrategy strategy) {
    this.strategy = strategy;
  }


  public RPNCalculatorService() {
    strategyMap.put("+", new AddOperation());
    strategyMap.put("*", new MultiplyOperation());
    strategyMap.put("-", new SubtractionOperation());
    strategyMap.put("/", new DivisionOperation());
    strategyMap.put("%", new ModuloOperation());
    strategyMap.put("^", new PowerOperation());
    strategyMap.put("!", new FactorialOperation());
  }

  public double calculate(String input) throws InvalidInputException {
    try {
      String[] inputs = input.split(",");

      if (inputs.length == 1 && isValidNumber(inputs[0])) {
        return Double.parseDouble(inputs[0]);
      }

      Deque<Double> queue = new ArrayDeque<>();

      for (String token : inputs) {
        token = token.trim();

        if (isValidNumber(token)) {
          queue.push(Double.parseDouble(token));

        } else if (!strategyMap.containsKey(token)){
          throw new IllegalArgumentException(UNSUPPORTED_OPERATOR + token);

        } else {
          this.setStrategy(strategyMap.get(token));
          queue.push(this.calculate(queue));
        }

      }

      if (queue.size() > 1) {
        throw new InvalidInputException("All inputs can not be processed");
      }

      return queue.pop();

    } catch (InvalidInputException | IllegalArgumentException e) {
      throw e;

    }  catch (Exception e) {
      throw new InvalidInputException(e);
    }
  }

  private double calculate(Deque<Double> queue) throws InvalidInputException {

    if (CalculationType.BINARY == strategy.getType()) {
      return ((RPNCalculationBinaryStrategy)strategy).calculate(queue.pop(), queue.pop());
    }

    return ((RPNCalculationUnaryStrategy)strategy).calculate(queue.pop());
  }

  private boolean isValidNumber(String str) {

    try {
      Double.parseDouble(str);
      return true;

    } catch (NumberFormatException e) {
      return false;
    }
  }
}
