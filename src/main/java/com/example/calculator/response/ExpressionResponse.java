package com.example.calculator.response;

public class ExpressionResponse {
  private Double result;

  public ExpressionResponse(final Double result) {
    this.result = result;
  }

  public Double getResult() {
    return result;
  }

  public void setResult(final Double result) {
    this.result = result;
  }
}
