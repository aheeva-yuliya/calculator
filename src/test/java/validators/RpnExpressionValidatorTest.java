package validators;

import com.example.calculator.service.rpn.exception.InvalidInputException;
import com.example.calculator.validators.RpnExpressionValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RpnExpressionValidatorTest {

  @Test
  void should_allow_when_valid_input_given() {
    Assertions.assertDoesNotThrow(
        ()-> new RpnExpressionValidator().validate("2,2,+"));
  }

  @Test
  void should_allow_when_just_one_element() {
    Assertions.assertDoesNotThrow(
        ()-> new RpnExpressionValidator().validate("2"));
  }


  @Test
  void should_throw_when_empty_expression() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      new RpnExpressionValidator().validate("");
    });
  }

  @Test
  void should_throw_when_expression_is_null() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      new RpnExpressionValidator().validate(null);
    });
  }

  @Test
  void should_throw_when_input_with_unexpected_separator_with_one_valid_splitter() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      new RpnExpressionValidator().validate("2,2;3");
    });
  }

  @Test
  void should_throw_when_input_with_unexpected_separator() {
    Assertions.assertThrows(InvalidInputException.class, () -> {
      new RpnExpressionValidator().validate("2;2");
    });
  }
}