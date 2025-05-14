import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ExchangeRateApiClient implements CurrencyApiClient {

    private final String apiKey;

    public ExchangeRateApiClient() {
        this.apiKey = ConfigLoader.get("API_KEY");
    }

    @Override
    public double getConversionRate(String from, String to) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + from + "/" + to;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder json = new StringBuilder();
            while (scanner.hasNext()) {
                json.append(scanner.nextLine());
            }
            scanner.close();

            JsonObject jsonObject = JsonParser.parseString(json.toString()).getAsJsonObject();
            return jsonObject.get("conversion_rate").getAsDouble();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter taxa de conversão: " + e.getMessage());
        }
    }
}
