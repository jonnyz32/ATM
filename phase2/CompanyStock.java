import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyStock {
	// Represents the shares you own of a specific company. Need a list since you can purchase shares at diff prices
	private String symbol;
	private List companyShares;

	public CompanyStock(String symbol, Share share){
		this.symbol = symbol;
		companyShares = new ArrayList();
		companyShares.add(share);
	}

	public void addShares(Share shares){
		companyShares.add(shares);
	}

	public String getSymbol(){
		return symbol;
	}
}
