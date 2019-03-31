import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter implements Serializable {
    private StockFetcher stockFetcher;

    String currencySymbolGetter(String currency){
        Map<String, String> currencySymbol = new HashMap<>();
        currencySymbol.put("EURO", "EURCAD");
        currencySymbol.put("YUAN", "CNYCAD");
        currencySymbol.put("RUBLE", "RUBCAD");
        currencySymbol.put("BRITISH POUND", "GBPCAD");
        currencySymbol.put("MEXICAN PESO", "MXNCAD");
        currencySymbol.put("USD", "USDCAD");
        return currencySymbol.get(currency);
    }

    CurrencyConverter(StockFetcher stockFetcher){
        this.stockFetcher = stockFetcher;
    }

    double convertCurrency(String currency, double amount) throws BadInputException{
        return  amount * stockFetcher.getPrice(currency);
    }
}
