import javafx.util.Pair;

import java.util.*;

public class CompanyStock {
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
	}

	public String getSymbol(){
		return symbol;
	}

	// Returns whether the amount of shares are owned
	public boolean has(int shareCount){
		return this.shareCount >= shareCount;
	}


	// Sell specified amount of shares and return totalPriceBoughtAt
	public double sell(int shareCount){
		sortSharesByPrice();
		int sharesLeftToSell = shareCount;
		int totalBoughtPrice = 0;

		ArrayList<Share> sharesToDelete = new ArrayList<>();

		for (Share share : companyShares){
			if (sharesLeftToSell > 0){
				if (share.getAmountOfShares() <= sharesLeftToSell){
					share.removeShares(share.getAmountOfShares());
					totalBoughtPrice += share.getBoughtAt() * share.getAmountOfShares();
					sharesLeftToSell -= share.getAmountOfShares();

					sharesToDelete.add(share);

				} else{
					share.removeShares(sharesLeftToSell);
					totalBoughtPrice += share.getBoughtAt() * share.getAmountOfShares();
					sharesLeftToSell = 0;

				}
			}
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

}
