package com.example.javaalkalmazasokeloadas.model.temp;

public class TempRequestDto {
    private String temp1Name = "";
    private Double temp1Value = 0.0;
    private String temp2Name = "";
    private Double temp2Value = 0.0;

    public TempRequestDto() {}

    public TempRequestDto(String temp1Name, Double temp1Value, String temp2Name, Double temp2Value) {
        this.temp1Name = temp1Name;
        this.temp1Value = temp1Value;
        this.temp2Name = temp2Name;
        this.temp2Value = temp2Value;
    }

    public String getTemp1Name() {
        return temp1Name;
    }

    public void setTemp1Name(String temp1Name) {
        this.temp1Name = temp1Name;
    }

    public Double getTemp1Value() {
        return temp1Value;
    }

    public void setTemp1Value(Double temp1Value) {
        this.temp1Value = temp1Value;
    }

    public String getTemp2Name() {
        return temp2Name;
    }

    public void setTemp2Name(String temp2Name) {
        this.temp2Name = temp2Name;
    }

    public Double getTemp2Value() {
        return temp2Value;
    }

    public void setTemp2Value(Double temp2Value) {
        this.temp2Value = temp2Value;
    }
}
