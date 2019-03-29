import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

public class StockAccount extends GenericAccount implements Serializable {

	private HashMap<String, CompanyStock> portfolio = new HashMap<>();
	private StockFetcher stockFetcher;
	private double profitFromTrading;

	public StockAccount(String name_p, IAccountHolder o) {
		super(name_p, o);
		asset = true;
		type = "(Stock)";
		withdrawable = new CannotWithdraw();
		//////////////////////////////////////////////////////
		stockFetcher = new StockFetcher("240UNLH6CSLKUUKH");
		profitFromTrading = 0.0;
	}

	public HashMap<String, CompanyStock> getPortfolio(){
		return portfolio;
	}

	public void viewPortfolio(){
		for (CompanyStock stock : portfolio.values()){
			System.out.println(stock.getSymbol());
			for (Share share : stock.getCompanyShares()){
				System.out.println("Amount of shares: " + share.getAmountOfShares() + " bought at " + share.getBoughtAt());
			}
		}
	}
	public double checkSymbolPrice(String symbol) throws BadInputException {
		try {
			HashMap stockInfo = stockFetcher.getCurrentStockInfo(symbol);
			double price = (double) stockInfo.get("price");
			System.out.println(price);
			return price;

		} catch (IOException e) {
			throw new BadInputException("Symbol Does Not Exist");
		}
	}
	public void checkSymbolDetailedInfo(String symbol) throws BadInputException {
		try {
			HashMap<String, Double> stockInfo = stockFetcher.getCurrentStockInfo(symbol);
			double price = stockInfo.get("price");
			double open = stockInfo.get("open");
			double high = stockInfo.get("high");
			double low = stockInfo.get("low");
			double volume = stockInfo.get("volume");
			double change = stockInfo.get("change");


			System.out.println("Price: " + price + " Open: " + open+ " High: " + high+ " Low: " + low+ " Volume"  + volume+ " Change:" + change);

		} catch (IOException e) {
			throw new BadInputException("Symbol Does Not Exist");
		}
	}
	public void viewProfit(){
		System.out.println(this.profitFromTrading);
	}

	public void purchaseShares(String symbol, int shares) throws BadInputException {
		try {
			HashMap<String, Double> stockInfo = stockFetcher.getCurrentStockInfo(symbol);

			// attempt to buy shares if enough funds
			if (getBalance() >= (shares * stockInfo.get("price"))){
				Share boughtShares = new Share(symbol, stockInfo.get("price"), shares);
				addToPortfolio(boughtShares);

				this.balance -= (shares * stockInfo.get("price"));

				System.out.println("SUCCESS");

			} else {

				throw new BadInputException("Not Enough Balance");

			}

		} catch (IOException e) {
			throw new BadInputException("Symbol Does Not Exist");
		}
	}

	public void sellShares(String symbol, int shares) throws BadInputException {
		double currentPrice = checkSymbolPrice(symbol);
		if (portfolio.containsKey(symbol)){
			CompanyStock companyStock = portfolio.get(symbol);
			if (companyStock.has(shares)){
				// update total profit made from trading
				double totalCostOfBuyingTheseShares = companyStock.sell(shares);
				double priceSoldFor = shares * currentPrice;
				this.profitFromTrading += priceSoldFor - totalCostOfBuyingTheseShares;
				this.balance += priceSoldFor- totalCostOfBuyingTheseShares;

			} else{
				throw new BadInputException("You don't own enough shares");
			}

		} else{
			throw new BadInputException("You don't own this stock");
		}
	}

	private void addToPortfolio(Share shares){
		if (portfolio.containsKey(shares.getSymbol())){
			portfolio.get(shares.getSymbol()).addShares(shares);
		} else{
			portfolio.put(shares.getSymbol(), new CompanyStock(shares.getSymbol(), shares));
		}
	}
}
