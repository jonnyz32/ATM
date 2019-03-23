import java.util.Map;

public class CurrencyConverter {
    private StockFetcher stockFetcher;

    CurrencyConverter(StockFetcher stockFetcher){
        this.stockFetcher = stockFetcher;
    }
    double convertCurrency(String currency, int amount) throws Exception{
        return  amount * (double) stockFetcher.getCurrentStockInfo(currency).get("price");
    }
}
