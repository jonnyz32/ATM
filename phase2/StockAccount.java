import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class StockAccount extends GenericAccount implements Serializable {

	private HashMap<String, CompanyStock> portfolio = new HashMap<>();
	private StockFetcher stockFetcher;
	private double profitFromTrading;

	public StockAccount(String name_p, IAccountHolder o) {
		name = name_p;
		owner = o;
		past_trans = new ArrayList<String>();
		balance = 0;
		asset = true;
		creation_date = new GregorianCalendar();
		lastTransText = "No transactions have been made";
		past_trans.add(lastTransText);
		type = "(Stock)";
		//////////////////////////////////////////////////////
		stockFetcher = new StockFetcher("240UNLH6CSLKUUKH");
		profitFromTrading = 0.0;
	}

	public void purchaseShares(String symbol, int shares){
		try {
			HashMap<String, Double> stockInfo = stockFetcher.getCurrentStockInfo(symbol);

			// attempt to buy shares if enough funds
			if (getBalance() >= (shares * stockInfo.get("price"))){
				Share boughtShares = new Share(symbol, stockInfo.get("price"), shares);
				addToPortfolio(boughtShares);

				System.out.println("SUCCESS");

			} else {

				System.out.println("FAILURE");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sellShares(String symbol, int shares){
		try {
			double currentPrice = (double) stockFetcher.getCurrentStockInfo(symbol).get("price");
			if (portfolio.containsKey(symbol)){
				CompanyStock companyStock = portfolio.get(symbol);
				if (companyStock.has(shares)){
					companyStock.sell(shares);
				} else{
					System.out.println("You do not own enough shares.");
				}

			} else{
				System.out.println("You do not own shares of this stock. ");
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addToPortfolio(Share shares){
		if (portfolio.containsKey(shares.getSymbol())){
			portfolio.get(shares.getSymbol()).addShares(shares);
		} else{
			portfolio.put(shares.getSymbol(), new CompanyStock(shares.getSymbol(), shares));
		}
	}
}
