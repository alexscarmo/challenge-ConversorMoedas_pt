public class CurrencyConverter {

    private final CurrencyApiClient apiClient;

    public CurrencyConverter(CurrencyApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public double convert(String from, String to, double amount) {
        double rate = apiClient.getConversionRate(from, to);
        return amount * rate;
    }
}

