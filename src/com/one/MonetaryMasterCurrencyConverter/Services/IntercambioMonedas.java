package com.one.MonetaryMasterCurrencyConverter.Services;

public class IntercambioMonedas {

    private String baseCode;

    private String targetCode;

    private double montoConsultar;

    private double tasaConversion;

    private double resultadoConversion;

    // Getters:
    public String getBaseCode() {
        return baseCode;
    }
    public String getTargetCode() {
        return targetCode;
    }
    public double getMontoConsultar() {
        return montoConsultar;
    }
    public double getMontoConvertido() {
        return tasaConversion;
    }
    public double getResultadoConversion() {
        return resultadoConversion;
    }

    // Setters:
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }
    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }
    public void setMontoConsultar(double montoConsultar) {
        this.montoConsultar = montoConsultar;
    }
    public void setTasaConversion(double tasaConversion) {
        this.tasaConversion = tasaConversion;
    }
    public void setResultadoConversion(double resultadoConversion) {
        this.resultadoConversion = resultadoConversion;
    }

    /*
    public void ConversorMoneda(String baseCode, String targetCode, double montoConsultar) {
        this.setBaseCode(baseCode);
        this.setTargetCode(targetCode);
        this.setMontoConsultar(montoConsultar);
    } */

    @Override
    public String toString() {
        return "\nMoneda Base: " + baseCode +
        "\nMoneda a convertir: " + targetCode +
        "\nMonto a consultar: $" + montoConsultar + " " + baseCode +
        "\nTasa de cambio: $" + tasaConversion + " " + targetCode +
        "\nResultado de la conversión: $" + resultadoConversion + " " + targetCode + "\n";
    }
}
