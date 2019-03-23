import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private StockFetcher stockFetcher;

    String currencySymbolGetter(String currency){
        Map<String, String> currencySymbol = new HashMap<>();
        currencySymbol.put("EUR", "EURCAD");
        currencySymbol.put("YUAN", "CNYCAD");
        currencySymbol.put("RUBLE", "RUBCAD");
        currencySymbol.put("BRITISH POUND", "GBPCAD");
        return currencySymbol.get(currency);
    }

    CurrencyConverter(StockFetcher stockFetcher){
        this.stockFetcher = stockFetcher;
    }

    double convertCurrency(String currency, double amount) throws Exception{
        return  amount * (double) stockFetcher.getCurrentStockInfo(currency).get("price");
    }
}
