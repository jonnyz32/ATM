import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;

public class StockFetcher {
	private String apiKey = "240UNLH6CSLKUUKH";

	public StockFetcher(String apiKey){
		this.apiKey = apiKey;
	}

	private String generateEndpoint(String symbol){
		return "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
	}

	public StringBuffer getCurrentStockPrice(String symbol) throws IOException {
		URL url = new URL(generateEndpoint(symbol));
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();

		con.disconnect();

		return content;
	}


}