

public class Share {
	private String symbol;
	private double boughtAt;
	private double amountOfSharesOwned;

	public Share(String symbol, double boughtAt, double amountOfSharesOwned){
		this.symbol = symbol;
		this.boughtAt = boughtAt;
		this.amountOfSharesOwned = amountOfSharesOwned;
	}

	public String getSymbol() {
		return symbol;
	}

	public double getBoughtAt() {
		return boughtAt;
	}


	public double getAmountOfSharesOwned() {
		return amountOfSharesOwned;
	}



	public void removeShares(int numOfShares){
		amountOfSharesOwned -= numOfShares;
	}

}
