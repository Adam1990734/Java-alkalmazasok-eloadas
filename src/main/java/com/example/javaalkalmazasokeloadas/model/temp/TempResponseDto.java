package com.example.javaalkalmazasokeloadas.model.temp;

public class TempResponseDto {
    private Double celsius;
    private Double fahrenheit;
    private TempConvertOp op;

    public TempResponseDto() {}

    public TempResponseDto(TempConvertOp op, Double celsius, Double fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
        this.op = op;
    }

    public Double getCelsius() {
        return celsius;
    }

    public void setCelsius(Double celsius) {
        this.celsius = celsius;
    }

    public Double getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(Double fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public TempConvertOp getOp() {
        return op;
    }

    public void setOp(TempConvertOp op) {
        this.op = op;
    }
}
