package com.example.calculator.service;


import com.example.calculator.service.rpn.RPNCalculatorService;
import com.example.calculator.service.rpn.exception.InvalidInputException;
import com.example.calculator.service.rpn.operation.OperationFactory;
import com.example.calculator.validators.NumberExpressionValidator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class RPNCalculatorServiceTest {
  private final OperationFactory operationFactory = new OperationFactory();
  private final NumberExpressionValidator numberExpressionValidator = new NumberExpressionValidator();


  static Stream<Arguments> checkMultiArgumentsMethodSource() {

    return Stream.of(Arguments.of("1, 2, 3, +, -",-4.0),
        Arguments.of("6, 2, *, 3, /",4),
        Arguments.of("2, 3, ^, 4, 5, +, +" ,17),
        Arguments.of("3, !, 4, 5, *, +", 26),
        Arguments.of("12, 3, /, !",24),
        Arguments.of("5, 1, 2, +, 4, *, +, 3, -",14));
  }

  @Test
  void should_throw_exception_when_empty_input() {
    Assertions.assertThrows(InvalidInputException.class, () -> new RPNCalculatorService(operationFactory, numberExpressionValidator).calculate(","));
  }

  @Test
  void should_throw_exception_when_null_input() {
    Assertions.assertThrows(InvalidInputException.class, () -> new RPNCalculatorService(operationFactory, numberExpressionValidator).calculate(
        null));
  }

  @Test
  void should_return_input_when_input_is_just_one_number() throws InvalidInputException {
    Assertions.assertEquals(4.5D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator)
        .calculate("4.5"));
  }

  @Test
  void should_throw_exception_when_invalid_input_given() {
    Assertions.assertThrows(InvalidInputException.class, () -> new RPNCalculatorService(operationFactory, numberExpressionValidator).calculate("2,+,2"));
  }

  @Test
  void should_calculate_rpn_given_valid_inputs() throws InvalidInputException {
    Assertions.assertEquals(-4.0, new RPNCalculatorService(operationFactory,
        numberExpressionValidator)
        .calculate("1,2,3,+,-"));
  }

  @Test
  void should_calculate_rpn_given_valid_inputs34() throws InvalidInputException {
    Assertions.assertEquals(-4.0, new RPNCalculatorService(operationFactory,
        numberExpressionValidator)
        .calculate("1,2,3,+,-"));
  }

  @Test
  void should_throw_exception_when_dividing_by_zero() {
    final InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> new RPNCalculatorService(operationFactory, numberExpressionValidator).calculate("0,0,0,/,-"));

    Assertions.assertEquals("java.lang.ArithmeticException: Division by zero is not allowed",
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_unsupported_operator_in_input() {
    final IllegalArgumentException invalidInputException = Assertions.assertThrows(
        IllegalArgumentException.class, () -> new RPNCalculatorService(operationFactory, numberExpressionValidator).calculate("0,0,0,_,-"));

    Assertions.assertTrue(
        invalidInputException.getMessage().startsWith(OperationFactory.UNSUPPORTED_OPERATOR));
  }

  @Test
  void should_calculate_addition() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("2,2,+"));
  }

  @Test
  void should_calculate_addition_with_signed_values() throws InvalidInputException {
    Assertions.assertEquals(0.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("-2,2,+"));
  }

  @Test
  void should_calculate_addition_with_signed_values2() throws InvalidInputException {
    Assertions.assertEquals(0.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("2,-2,+"));
  }

  @Test
  void should_calculate_addition_with_signed_values3() throws InvalidInputException {
    Assertions.assertEquals(1.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("-2,3,+"));
  }

  @Test
  void should_calculate_addition_with_signed_values4() throws InvalidInputException {
    Assertions.assertEquals(-2.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("-5,3,+"));
  }

  @Test
  void should_calculate_subtraction() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("6,2,-"));
  }

  @Test
  void should_calculate_subtraction_with_signed_values() throws InvalidInputException {
    Assertions.assertEquals(-4.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("-2,2,-"));
  }

  @Test
  void should_calculate_subtraction_with_signed_values2() throws InvalidInputException {
    Assertions.assertEquals(-7.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("-5,2,-"));
  }

  @Test
  void should_calculate_division() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("8,2,/"));
  }

  @Test
  void should_calculate_multiplication() throws InvalidInputException {
    Assertions.assertEquals(4.0D, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate("2,2,*"));
  }

  @Test
  void should_calculate_rpn_given_valid_inputs2() {
    InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> new RPNCalculatorService(operationFactory, numberExpressionValidator).calculate("1,2,3,+,-,%"));

    Assertions.assertEquals("java.util.NoSuchElementException",
        invalidInputException.getMessage());
  }

  @Test
  void should_throw_exception_when_count_of_operator_is_greater_than_numbers() {
    InvalidInputException invalidInputException = Assertions.assertThrows(
        InvalidInputException.class, () -> new RPNCalculatorService(operationFactory, numberExpressionValidator).calculate("1,2,-,+,-"));

    Assertions.assertEquals("java.util.NoSuchElementException",
        invalidInputException.getMessage());
  }

  @ParameterizedTest
  @MethodSource("checkMultiArgumentsMethodSource")
  void should_correctly_evaluate_rpn_values(String input, double expected)
      throws InvalidInputException {
    Assertions.assertEquals(expected, new RPNCalculatorService(operationFactory,
        numberExpressionValidator).calculate(input));
  }
}
