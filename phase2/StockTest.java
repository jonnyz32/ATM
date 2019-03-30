import org.junit.Assert;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.HashMap;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class StockTest {
	@Before
	public void setUp(){

	}

	@After
	public void tearDown() {
	}

	private void purchaseShares(StockAccount stockAccount, String symbol, int num){
        try {
            stockAccount.purchaseShares(symbol, num);
        }
        catch(BadInputException e){
            System.out.println("This really shouldn't happen");
        }
    }

    private void sellShares(StockAccount stockAccount, String symbol, int num){
        try {
            stockAccount.sellShares(symbol, num);
        }
        catch(BadInputException e){
            System.out.println("This really shouldn't happen");
        }
    }

    private double getPrice(String symbol){
        StockFetcher sf =  new StockFetcher("240UNLH6CSLKUUKH");
        try {
            return sf.getPrice(symbol);
        }
        catch(BadInputException e){
            System.out.println("This really shouldn't happen");
            return -1;
        }
    }

	@Test
	public void testGetCurrentStockInfo(){
		StockFetcher stockFetcher = new StockFetcher("240UNLH6CSLKUUKH");
		try {
			HashMap map = stockFetcher.getCurrentStockInfo("MSFT");
			assertEquals(map.size(), 6);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSellCompanyStock(){

	}

	@Test
	public void testSortCompanyStockByPrice(){
		CompanyStock companyStock = new CompanyStock("MSFT", new Share("MSFT", 200.0, 5));
		companyStock.addShares(new Share("MSFT", 10.0, 3));

		companyStock.sortSharesByPrice();

		assertEquals(10.0,companyStock.getCompanyShares().get(0).getBoughtAt(), 0.01);
		assertEquals(200.0,companyStock.getCompanyShares().get(1).getBoughtAt(),0.01);

	}


	@Test
	public void testProfit(){
		Customer customer = Mockito.mock(Customer.class);
		StockAccount stockAccount = new StockAccount("test",customer);
		purchaseShares(stockAccount,"MSFT", 10);
	}

	@Test
	public void testPurchaseShares(){
		Customer customer = Mockito.mock(Customer.class);
		StockAccount stockAccount = new StockAccount("test",customer);
		stockAccount.depositCheque(1000.0);
		assertEquals(1000.0, stockAccount.balance, 0);
		purchaseShares(stockAccount,"MSFT", 2);
		assertEquals(1000.0 - (2 * getPrice("MSFT")), stockAccount.balance, 0.5);
		// TODO finish this and the withdraw line in stock account ^^

		assertEquals(2, stockAccount.getPortfolio().get("MSFT").getShareCount());
	}

	@Test
	public void testSellShares(){
		Customer customer = Mockito.mock(Customer.class);
		StockAccount stockAccount = new StockAccount("test",customer);
		stockAccount.depositCheque(10000.0);
		assertEquals(10000.0, stockAccount.balance, 0);
		purchaseShares(stockAccount,"MSFT", 2);

		sellShares(stockAccount,"MSFT", 1);
		assertEquals(10000.0 - (getPrice("MSFT")), stockAccount.balance, 5);

		assertEquals(1, stockAccount.getPortfolio().get("MSFT").getShareCount());


		purchaseShares(stockAccount,"MSFT", 10);
		assertEquals(2, stockAccount.getPortfolio().get("MSFT").getCompanyShares().size());

	}

	@Test
	public void testAddToPortfolio(){

	}

}
