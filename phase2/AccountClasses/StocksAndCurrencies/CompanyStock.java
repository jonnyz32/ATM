package AccountClasses.StocksAndCurrencies;

import java.io.Serializable;
import java.util.*;

public class CompanyStock implements Serializable {
	// Represents the shares you own of a specific company. Need a list since you can purchase shares at diff prices
	private String symbol;
	private List<Share> companyShares;
	private int shareCount;

	public CompanyStock(String symbol, Share share){
		this.symbol = symbol;
		companyShares = new ArrayList();
		companyShares.add(share);
		shareCount = share.getAmountOfShares();
	}

	public void addShares(Share shares){
		companyShares.add(shares);
		shareCount += shares.getAmountOfShares();
		updateShareCount();
	}

	public String getSymbol(){
		return symbol;
	}

	// Returns whether the amount of shares are owned
	public boolean has(int shareCount){
		updateShareCount();
		return this.shareCount >= shareCount;
	}

	public List<Share> getCompanyShares() {
		return companyShares;
	}

	public int getShareCount() {
		updateShareCount();
		return shareCount;
	}

	// Sell specified amount of shares and return totalPriceBoughtAt
	public double sell(int shareCount){
		sortSharesByPrice();
		int sharesLeftToSell = shareCount;
		double totalBoughtPrice = 0;

		ArrayList<Share> sharesToDelete = new ArrayList<>();

		for (Share share : companyShares){
			if (sharesLeftToSell > 0){
				if (share.getAmountOfShares() <= sharesLeftToSell){
					totalBoughtPrice += share.getBoughtAt() * share.getAmountOfShares();
					sharesLeftToSell -= share.getAmountOfShares();

					share.removeShares(share.getAmountOfShares());
					sharesToDelete.add(share);

				} else{
					totalBoughtPrice += share.getBoughtAt() * sharesLeftToSell;
					share.removeShares(sharesLeftToSell);
					sharesLeftToSell = 0;
				}
			}
			updateShareCount();
		}

		// Delete empty shares from portfolio
		for (Share s : sharesToDelete){
			companyShares.remove(s);
		}

		// returns the
		return totalBoughtPrice;
	}

	public void sortSharesByPrice(){
		Collections.sort(companyShares, new Comparator<Share>() {
			public int compare(Share s1, Share s2){
				return (int) (s1.getBoughtAt() - s2.getBoughtAt());
			}
		});
	}

	public boolean checkIfEmpty(){
		return shareCount == 0;
	}

	public void updateShareCount(){
		int total = 0;
		for (Share share : companyShares){
			total += share.getAmountOfShares();
		}
		this.shareCount = total;
	}

}
