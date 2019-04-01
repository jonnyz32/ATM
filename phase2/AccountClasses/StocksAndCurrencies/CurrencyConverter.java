package AccountClasses.StocksAndCurrencies;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter implements Serializable {
    private StockFetcher stockFetcher;

    public String currencySymbolGetter(String currency){
        Map<String, String> currencySymbol = new HashMap<>();
        currencySymbol.put("EURO", "EURCAD");
        currencySymbol.put("YUAN", "CNYCAD");
        currencySymbol.put("RUBLE", "RUBCAD");
        currencySymbol.put("BRITISH POUND", "GBPCAD");
        currencySymbol.put("MEXICAN PESO", "MXNCAD");
        currencySymbol.put("USD", "USDCAD");
        return currencySymbol.get(currency);
    }

    public CurrencyConverter(StockFetcher stockFetcher){
        this.stockFetcher = stockFetcher;
    }

    public double convertCurrency(String currency, double amount){
        return  amount * stockFetcher.getPrice(currency);
    }
}
