import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class StockAccount extends GenericAccount implements Serializable {

	private HashMap<String, CompanyStock> portfolio = new HashMap<>();
	private StockFetcher stockFetcher;
	private double profitFromTrading;

	public StockAccount(String name_p, IAccountHolder o) {
		super(name_p, o);
		asset = true;
		type = "STOCK";
		withdrawable = new CannotWithdraw();
		//////////////////////////////////////////////////////
		stockFetcher = new StockFetcher("240UNLH6CSLKUUKH");
		profitFromTrading = 0.0;
	}

	public HashMap<String, CompanyStock> getPortfolio(){
		return portfolio;
	}

	public String getSummary() {
		return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
				+ "Asset: " + asset + "\n" + "Balance: " + balance + "\n" + "Last Transaction: " + getLatestTransaction()
				+ "\n" + "Profit from Trading: " + profitFromTrading + "\n" + "Companies in portfolio: " + portfolio.size();
	}

	public void viewPortfolio(){
		for (CompanyStock stock : portfolio.values()){
			JFrame notice = new JFrame();
	        String infoMessage = stock.getSymbol();
	        
			for (Share share : stock.getCompanyShares()){
				infoMessage += "\n Amount of shares: " + share.getAmountOfShares() + " bought at " + share.getBoughtAt();
			}
			JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public double checkSymbolPrice(String symbol) throws BadInputException {
		try {
			
	        if(symbol == null) {
	        	BankManagerMenuGUI.showInputError();
	        	throw new BadInputException("Nothing inputted");
	        }
			
			HashMap stockInfo = stockFetcher.getCurrentStockInfo(symbol);
			double price = (double) stockInfo.get("price");
			JFrame notice = new JFrame();
	        String infoMessage = Double.toString(price);
	        JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
			return price;

		} catch (IOException e) {
			throw new BadInputException("Symbol Does Not Exist");
		}
	}
	public void checkSymbolDetailedInfo(String symbol) throws BadInputException {
		try {
			if(symbol == null) {
				BankManagerMenuGUI.showInputError();
				throw new BadInputException("Nothing inputted");
			}

			HashMap<String, Double> stockInfo = stockFetcher.getCurrentStockInfo(symbol);
			double price = stockInfo.get("price");
			double open = stockInfo.get("open");
			double high = stockInfo.get("high");
			double low = stockInfo.get("low");
			double volume = stockInfo.get("volume");
			double change = stockInfo.get("change");

			JFrame notice = new JFrame();
	        String infoMessage = "Price: " + price + " Open: " + open+ " High: " + high+ " Low: " + low+ " Volume"  + volume+ " Change:" + change;
	        JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
	        
			
		} catch (IOException e) {
			throw new BadInputException("Symbol Does Not Exist");
		}
	}
	public void viewProfit(){
		JFrame notice = new JFrame();
        String infoMessage = Double.toString(this.profitFromTrading);
        JOptionPane.showMessageDialog(null, infoMessage, null, JOptionPane.INFORMATION_MESSAGE);
	}

	public void purchaseShares(String symbol, int numShares) throws BadInputException {
		try {
			HashMap<String, Double> stockInfo = stockFetcher.getCurrentStockInfo(symbol);

			// attempt to buy shares if enough funds
			if (getBalance() >= (numShares * stockInfo.get("price"))){
				Share boughtShares = new Share(symbol, stockInfo.get("price"), numShares);
				addToPortfolio(boughtShares);

				this.balance -= (numShares * stockInfo.get("price"));

				BankManagerMenuGUI.showSuccess();

			} else {

				throw new BadInputException("Not Enough Balance");

			}

		} catch (IOException e) {
			throw new BadInputException("Symbol Does Not Exist");
		}
	}

	public void sellShares(String symbol, int numShares) throws BadInputException {
		double currentPrice = checkSymbolPrice(symbol);
		if (portfolio.containsKey(symbol)){
			CompanyStock companyStock = portfolio.get(symbol);
			if (companyStock.has(numShares)){
				// update total profit made from trading
				double totalCostOfBuyingTheseShares = companyStock.sell(numShares);
				double priceSoldFor = numShares * currentPrice;
				System.out.println(totalCostOfBuyingTheseShares);
				System.out.println(priceSoldFor);
				this.profitFromTrading += priceSoldFor - totalCostOfBuyingTheseShares;
				this.balance += priceSoldFor- totalCostOfBuyingTheseShares;
				companyStock.updateShareCount();
				cleanPortfolio();

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

	private void cleanPortfolio(){
		ArrayList<String> toDelete = new ArrayList();
		for (String company : portfolio.keySet()){
			if (portfolio.get(company).checkIfEmpty()){
				toDelete.add(company);
			}
		}

		for (String c : toDelete){
			portfolio.remove(c);
		}
	}
}
