package com.example.javaalkalmazasokeloadas.model.temp;

public class TempResponseDto {
    private String temp1Name;
    private String temp1Value;
    private String temp2Name;
    private String temp2Value;

    public TempResponseDto() {}

    public TempResponseDto(String temp1Name, String temp1Value, String temp2Name, String temp2Value) {
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

    public String getTemp1Value() {
        return temp1Value;
    }

    public void setTemp1Value(String temp1Value) {
        this.temp1Value = temp1Value;
    }

    public String getTemp2Name() {
        return temp2Name;
    }

    public void setTemp2Name(String temp2Name) {
        this.temp2Name = temp2Name;
    }

    public String getTemp2Value() {
        return temp2Value;
    }

    public void setTemp2Value(String temp2Value) {
        this.temp2Value = temp2Value;
    }
}
