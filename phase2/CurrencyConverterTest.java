import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class CurrencyConverterTest {
    private CurrencyConverter currencyConverter;
    @Mock private StockFetcher stockFetcher;

    @Before
    public void setUp(){

        stockFetcher = Mockito.mock(StockFetcher.class);
        when(stockFetcher.getPrice("USDCAD")).thenReturn(1.337);
        currencyConverter = new CurrencyConverter(stockFetcher);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void convertCurrency(){
        double actual = currencyConverter.convertCurrency("USDCAD", 100);
        assertEquals(133.70, actual,0);
    }
}