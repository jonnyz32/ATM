import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


import static org.junit.Assert.*;



public class CurrencyConverterTest {
    private CurrencyConverter currencyConverter;
    private Map<String, Double> USDCADTable;
    @Mock private StockFetcher stockFetcher;

    @Before
    public void setUp() throws Exception{
        USDCADTable = new HashMap<>();
        USDCADTable.put("Slot1",1.0);
        USDCADTable.put("Slot2",1.0);
        USDCADTable.put("Slot3",1.0);
        USDCADTable.put("price",1.337);
        USDCADTable.put("Slot5",1.0);
        USDCADTable.put("Slot6",1.0);

        stockFetcher = Mockito.mock(StockFetcher.class);
        when(stockFetcher.getCurrentStockInfo("USDCAD")).thenReturn((HashMap)USDCADTable);
        currencyConverter = new CurrencyConverter(stockFetcher);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertCurrency() throws Exception {
        double actual = currencyConverter.convertCurrency("USDCAD", 100);
        assertEquals(133.70, actual,0);


    }
}