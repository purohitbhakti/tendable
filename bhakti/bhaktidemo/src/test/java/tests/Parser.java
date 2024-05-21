package tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class Parser {

	private FileInputStream stream;
	private String RepositoryFile;
	private Properties propertyFile = new Properties();

	public Parser(String fileName) throws IOException
	{
		this.RepositoryFile = fileName;
		stream = new FileInputStream(RepositoryFile);
		propertyFile.load(stream);
	}

	public By getObjectLocator(String locatorName)
	{
		String locatorProperty = propertyFile.getProperty(locatorName);
		String locatorType = locatorProperty.split("#")[0];
		String locatorValue = locatorProperty.split("#")[1];

		By locator = null;
		switch(locatorType)
		{
		case "Name":
			locator = By.name(locatorValue);
			break;
		case "LinkText":
			locator = By.linkText(locatorValue);
			break;
		case "Xpath":
			locator = By.xpath(locatorValue);
			break;
		}
		return locator;
	}
}
