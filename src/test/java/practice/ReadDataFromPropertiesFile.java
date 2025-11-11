package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromPropertiesFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("C:\\PRIYANKA\\Advanced Selenium\\Data\\Commondata.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER=prop.getProperty("Browser");
		String URL=prop.getProperty("URL");
		String USERNAME=prop.getProperty("Username");
		String PASSWORD=prop.getProperty("Password");
		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);

	}

}
