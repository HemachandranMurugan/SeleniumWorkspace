package fileReading;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigParser {
	
	private FileInputStream stream;
	private String RepositoryFile;
	private Properties propertyFile = new Properties();
	
	public ConfigParser (String fileName) throws IOException
	{
		this.RepositoryFile = fileName;
		stream = new FileInputStream(RepositoryFile);
		propertyFile.load(stream);
	}
	
	public String getObjectLocator(String locatorName) {
		return propertyFile.getProperty(locatorName);
	}
}
