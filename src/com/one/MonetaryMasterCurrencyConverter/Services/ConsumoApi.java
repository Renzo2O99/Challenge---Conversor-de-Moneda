package com.one.MonetaryMasterCurrencyConverter.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConsumoApi {
    public String linkCreate(String apiUrl, String apiKey, String baseCode, String targetCode, double amount) {
        return apiUrl + apiKey + "/pair/" + baseCode + "/" + targetCode + "/" + amount;
    }

    public String obtenerDatos(String url, IntercambioMonedas monedas) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }

    public IntercambioMonedas deserializarMoneda(String json, IntercambioMonedas monedas) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        
        monedas.setBaseCode(jsonObject.get("base_code").getAsString());
        monedas.setTargetCode(jsonObject.get("target_code").getAsString());
        monedas.setTasaConversion(jsonObject.get("conversion_rate").getAsDouble());
        monedas.setResultadoConversion(jsonObject.get("conversion_result").getAsDouble());
    
        return monedas;
    }
    
}
