package ia;

import java.awt.List;
import java.awt.Window.Type;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class tester {

	public tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String uniName = "Queen Mary University London";
			String formatedUniName = uniName.replaceAll("\\s","%20");
			
			String APIkey = "AIzaSyA0SQ2rQ94n57EEJkZ_eKZ6cNdgCCu1r1g";
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+formatedUniName+"&key=AIzaSyA0SQ2rQ94n57EEJkZ_eKZ6cNdgCCu1r1g");
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String jsonString = "";
			String strTemp = "";
			
			while (null != (strTemp = br.readLine())) {
				jsonString +=  strTemp;
			}
			
			JsonObject jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
			JsonArray resultsArray = jsonObject.getAsJsonArray("results");
			JsonObject resultsObject = (JsonObject) resultsArray.get(0);
			JsonArray addressArray = resultsObject.getAsJsonArray("address_components");
			String postCode = addressArray.get(6).getAsJsonObject().get("long_name").getAsString();
		       
			} catch (Exception ex) {
				ex.printStackTrace();
			}
	}
}

