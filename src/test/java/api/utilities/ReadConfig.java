package api.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

Properties properties;
String path ="C:\\Users\\Dines\\eclipse-workspace\\RestAssuredAutomationFramework_AID" ;

//constructor
public void readConfig(){
	try {
		properties= new Properties();
		FileInputStream fis  = new FileInputStream(path);
		properties.load(fis);	
	}	catch (Exception e) {
		e.printStackTrace();
	}
}

public String getBaseUrl() {
	String values = properties.getProperty("browser");
	return values;
}
}