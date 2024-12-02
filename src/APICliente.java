import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APICliente {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/YOUR_API_KEY/latest/";

    public double convertirMoneda(String baseCurrency, String targetCurrency, double cantidad) throws Exception {
        // Solicita las tasas de cambio para la moneda base
        Map<String, Double> rates = getExchangeRates(baseCurrency);
        // Verifica si la moneda de destino está en las tasas
        if (rates.containsKey(targetCurrency)) {
            double tasa = rates.get(targetCurrency);
            return cantidad * tasa; // Realiza la conversión
        } else {
            throw new Exception("Moneda no disponible para la conversión.");
        }
    }

    private Map<String, Double> getExchangeRates(String baseCurrency) throws Exception {
        String urlString = API_URL + baseCurrency;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("Error en la solicitud: Código " + responseCode);
        }

        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        Gson gson = new Gson();
        APIResponse response = gson.fromJson(reader, APIResponse.class);
        reader.close();

        return response.conversionRates;
    }

    private class APIResponse {
        Map<String, Double> conversionRates;
    }
}
