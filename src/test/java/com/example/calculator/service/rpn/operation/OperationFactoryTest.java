package com.example.calculator.service.rpn.operation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.calculator.service.rpn.strategy.RPNCalculationStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

class OperationFactoryTest {
  OperationFactory operationFactory = new OperationFactory();
  @Test
  void should_throw_when_invalid_operator() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> operationFactory.getOperation(":"));
  }

  @Test
  void should_get_operation_when_valid_operator() {
    RPNCalculationStrategy rpnCalculationStrategy = operationFactory.getOperation("+");
    Assertions.assertTrue(rpnCalculationStrategy instanceof AddOperation);
  }
}