package com.one.MonetaryMasterCurrencyConverter.Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.one.MonetaryMasterCurrencyConverter.Util.Menu;

public class App {
    public static void main(String[] args) throws IOException {

        // Instanciaciones:
        var entrada = new Scanner(System.in);
        var apiClient = new ApiClient();
        var menuInteractivo = new Menu();
        var consumoApi = new ConsumoApi();
        var monedas = new IntercambioMonedas();

        Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setPrettyPrinting()
        .create();

        // Menú Principal:
        while (true) {
            System.out.println(menuInteractivo);
            int opUsuario = entrada.nextInt();

            if (opUsuario > 0 && opUsuario < 7) {
                System.out.println("Ingrese a continuación el monto a consultar:");
                double montoConsultar = entrada.nextDouble();
                entrada.nextLine();
                monedas.setMontoConsultar(montoConsultar);

                System.out.println("\nAhora ingrese un nombre para almacenar el historial de sus conversiones:");
                var nombreHistorial = entrada.nextLine();
                
                switch (opUsuario) {
                    case 1:
                        monedas.setBaseCode("USD");
                        monedas.setTargetCode("ARS");
                        break;
                    case 2:
                        monedas.setBaseCode("ARS"); 
                        monedas.setTargetCode("USD");
                        break;
                    case 3:
                        monedas.setBaseCode("USD");
                        monedas.setTargetCode("BRL");
                        break;
                    case 4:
                        monedas.setBaseCode("BRL");
                        monedas.setTargetCode("USD");
                        break;
                    case 5:
                        monedas.setBaseCode("USD");
                        monedas.setTargetCode("COP");
                        break;
                    case 6:
                        monedas.setBaseCode("COP");
                        monedas.setTargetCode("USD");
                        break;
                    default:
                        break;
                }

                // Generamos el link:
                String url = consumoApi.linkCreate(apiClient.getLINKAPICLIENT(), apiClient.getAPIKEY(), monedas.getBaseCode(), monedas.getTargetCode(), monedas.getMontoConsultar());
                
                
                var jsonResponse = consumoApi.obtenerDatos(url, monedas);
                monedas = consumoApi.deserializarMoneda(jsonResponse, monedas);

                // Salida de Datos:
                System.out.println(monedas.toString());

                // Persistencia de datos:
                File directorio = new File("src/com/one/MonetaryMasterCurrencyConverter/Historial");
                if (!directorio.exists()) {
                    directorio.mkdir();
                }
                
                FileWriter escribirJson = new FileWriter("src/com/one/MonetaryMasterCurrencyConverter/Historial/" + nombreHistorial + ".json");
                gson.toJson(monedas, escribirJson);
                escribirJson.close();

                escribirJson.close();

            } else if (opUsuario == 7) {
                System.out.println("\n¡Fin del Programa!\n");
                break;
            } else {
                System.out.println("\nOpción inválida. Intente nuevamente.\n");
            }
        }

        // Cerramos el Scanner:
        entrada.close();
    }
}
