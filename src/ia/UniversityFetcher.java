package ia;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UniversityFetcher {

	public static void main(String[] args) {
		
	    try {
	        File universityList = new File("C:\\Users\\hesho\\Desktop\\universities.json"); // Creates file to store universities in JSON format
	        if (universityList.createNewFile()) { // If created successfully success message is outputed
	          System.out.println("Created: " + universityList.getName() + " file."); // 
	        } else {
	          System.out.println("File already exists.");
	        }
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		
		InputStream inputStream;
		try {
			inputStream = new URL("http://universities.hipolabs.com/search?country=united%20kingdom").openStream(); // Connects to Hippo API using search critieria of UK Unis only
			Files.copy(inputStream, Paths.get("C:\\\\Users\\\\hesho\\\\Desktop\\\\universities.json"), StandardCopyOption.REPLACE_EXISTING); // Updates file rewriting what exsisted previously
			System.out.println("File updated.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
