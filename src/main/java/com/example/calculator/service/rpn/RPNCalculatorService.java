package com.example.calculator.service.rpn;

import com.example.calculator.service.rpn.exception.InvalidInputException;
import com.example.calculator.service.rpn.operation.OperationFactory;

import com.example.calculator.service.rpn.strategy.RPNCalculationBinaryStrategy;
import com.example.calculator.service.rpn.strategy.RPNCalculationStrategy;
import com.example.calculator.service.rpn.strategy.RPNCalculationUnaryStrategy;
import com.example.calculator.validators.NumberExpressionValidator;
import java.util.ArrayDeque;
import java.util.Deque;
import org.springframework.stereotype.Service;

@Service
public class RPNCalculatorService {
  private final OperationFactory operationFactory;
  private final NumberExpressionValidator numberExpressionValidator;

  private RPNCalculationStrategy strategy;

  public RPNCalculatorService(OperationFactory operationFactory, NumberExpressionValidator numberExpressionValidator) {
    this.operationFactory = operationFactory;
    this.numberExpressionValidator = numberExpressionValidator;
  }

  public void setStrategy(RPNCalculationStrategy strategy) {
    this.strategy = strategy;
  }

  public double calculate(String input) throws InvalidInputException {
    try {
      String[] inputs = input.split(",");

      if (inputs.length == 1 && numberExpressionValidator.isValid((inputs[0]))) {
        return Double.parseDouble(inputs[0]);
      }

      Deque<Double> queue = new ArrayDeque<>();

      for (String token : inputs) {
        token = token.trim();

        if (numberExpressionValidator.isValid(token)) {
          queue.push(Double.parseDouble(token));

        } else {
          this.setStrategy(operationFactory.getOperation(token));
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
}
