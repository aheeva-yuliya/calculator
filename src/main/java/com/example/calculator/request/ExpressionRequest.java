package com.example.calculator.request;

public class ExpressionRequest {
  private String expression;

  public ExpressionRequest(final String expression) {
    this.expression = expression;
  }

  public ExpressionRequest() {
  }

  public String getExpression() {
    return expression;
  }

  public void setExpression(final String expression) {
    this.expression = expression;
  }
}
