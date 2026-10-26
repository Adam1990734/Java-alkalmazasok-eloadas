package com.example.javaalkalmazasokeloadas.model.temp;

public class TempResponseDto {
    private String celsius;
    private String fahrenheit;
    private TempConvertOp op;

    public TempResponseDto() {}

    public TempResponseDto(TempConvertOp op, String celsius, String fahrenheit) {
        this.celsius = celsius;
        this.fahrenheit = fahrenheit;
        this.op = op;
    }

    public String getCelsius() {
        return celsius;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
    }

    public String getFahrenheit() {
        return fahrenheit;
    }

    public void setFahrenheit(String fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    public TempConvertOp getOp() {
        return op;
    }

    public void setOp(TempConvertOp op) {
        this.op = op;
    }
}
