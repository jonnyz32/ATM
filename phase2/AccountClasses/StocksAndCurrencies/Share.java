package AccountClasses.StocksAndCurrencies;

import java.io.Serializable;

public class Share implements Serializable {
	private String symbol;
	private double boughtAt;
	private int amount;

	public Share(String symbol, double boughtAt, int amount){
		this.symbol = symbol;
		this.boughtAt = boughtAt;
		this.amount = amount;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getBoughtAt() {
		return boughtAt;
	}


	public int getAmountOfShares() {
		return amount;
	}


	public void removeShares(int numOfShares){
		amount-= numOfShares;
	}

}
