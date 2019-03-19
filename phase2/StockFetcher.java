import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StockFetcher {
	private String apiKey;

	public StockFetcher(String apiKey){
		this.apiKey = apiKey;
	}

	private String generateEndpoint(String symbol){
		return "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
	}

	private JsonObject stringToJson(String str){
		return new JsonParser().parse(str).getAsJsonObject();
	}

	public HashMap getCurrentStockInfo(String symbol) throws IOException {
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

		return jsonStockInfoToDict(stringToJson(content.toString()).get("Global Quote").getAsJsonObject());
	}

	private HashMap jsonStockInfoToDict(JsonObject apiJson){
		HashMap<String, Double> hashmap = new HashMap();
		hashmap.put("open", apiJson.get("02. open").getAsDouble());
		hashmap.put("high", apiJson.get("03. high").getAsDouble());
		hashmap.put("low", apiJson.get("04. low").getAsDouble());
		hashmap.put("price", apiJson.get("05. price").getAsDouble());
		hashmap.put("volume", apiJson.get("06. volume").getAsDouble());
		hashmap.put("change", apiJson.get("09. change").getAsDouble());

		return hashmap;
	}



}