package ia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class tester {

	public tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String apiResponse = "{\r\n" + 
				"   \"results\" : [\r\n" + 
				"      {\r\n" + 
				"         \"address_components\" : [\r\n" + 
				"            {\r\n" + 
				"               \"long_name\" : \"1600\",\r\n" + 
				"               \"short_name\" : \"1600\",\r\n" + 
				"               \"types\" : [ \"street_number\" ]\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"               \"long_name\" : \"Amphitheatre Parkway\",\r\n" + 
				"               \"short_name\" : \"Amphitheatre Pkwy\",\r\n" + 
				"               \"types\" : [ \"route\" ]\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"               \"long_name\" : \"Mountain View\",\r\n" + 
				"               \"short_name\" : \"Mountain View\",\r\n" + 
				"               \"types\" : [ \"locality\", \"political\" ]\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"               \"long_name\" : \"Santa Clara County\",\r\n" + 
				"               \"short_name\" : \"Santa Clara County\",\r\n" + 
				"               \"types\" : [ \"administrative_area_level_2\", \"political\" ]\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"               \"long_name\" : \"California\",\r\n" + 
				"               \"short_name\" : \"CA\",\r\n" + 
				"               \"types\" : [ \"administrative_area_level_1\", \"political\" ]\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"               \"long_name\" : \"United States\",\r\n" + 
				"               \"short_name\" : \"US\",\r\n" + 
				"               \"types\" : [ \"country\", \"political\" ]\r\n" + 
				"            },\r\n" + 
				"            {\r\n" + 
				"               \"long_name\" : \"94043\",\r\n" + 
				"               \"short_name\" : \"94043\",\r\n" + 
				"               \"types\" : [ \"postal_code\" ]\r\n" + 
				"            }\r\n" + 
				"         ],\r\n" + 
				"         \"formatted_address\" : \"1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA\",\r\n" + 
				"         \"geometry\" : {\r\n" + 
				"            \"location\" : {\r\n" + 
				"               \"lat\" : 37.422295,\r\n" + 
				"               \"lng\" : -122.084148\r\n" + 
				"            },\r\n" + 
				"            \"location_type\" : \"ROOFTOP\",\r\n" + 
				"            \"viewport\" : {\r\n" + 
				"               \"northeast\" : {\r\n" + 
				"                  \"lat\" : 37.42364398029149,\r\n" + 
				"                  \"lng\" : -122.0827990197085\r\n" + 
				"               },\r\n" + 
				"               \"southwest\" : {\r\n" + 
				"                  \"lat\" : 37.4209460197085,\r\n" + 
				"                  \"lng\" : -122.0854969802915\r\n" + 
				"               }\r\n" + 
				"            }\r\n" + 
				"         },\r\n" + 
				"         \"place_id\" : \"ChIJVYBZP-Oxj4ARls-qJ_G3tgM\",\r\n" + 
				"         \"types\" : [ \"street_address\" ]\r\n" + 
				"      }\r\n" + 
				"   ],\r\n" + 
				"   \"status\" : \"OK\"\r\n" + 
				"}";
		String APIkey = "AIzaSyA0SQ2rQ94n57EEJkZ_eKZ6cNdgCCu1r1g";
		try {
			//URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=AIzaSyA0SQ2rQ94n57EEJkZ_eKZ6cNdgCCu1r1g");
			//BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String strTemp = "";
			
			GsonBuilder builder = new GsonBuilder(); 
			builder.setPrettyPrinting(); 
			Gson gson = builder.create();
			String jsonString = "";
//			while (null != (strTemp = br.readLine())) {
//				System.out.println(strTemp);
//				jsonString +=  strTemp;
//				
//			}
			
			String finalStr = gson.toJson(apiResponse);
			Place place = gson.fromJson(apiResponse, Place.class);
			System.out.println(1);
			System.out.println(place);
			System.out.println(1);
			//System.out.println(finalStr);
			//System.out.println(strTemp);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

class Place {
	private String postal_code;
	public Place() {}
	
	public String getPostalCode() {
		return postal_code;
	}
	public void setPostalCode(String postalCode) {
		this.postal_code = postalCode;
	}
}
