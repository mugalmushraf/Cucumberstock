package commanFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class FunctionLibrary {
	public static WebDriver driver;

	public static WebDriver startBrowser() throws Throwable
	{
		System.setProperty("Webdriver.chrome.driver","chromedriver.exe");
		return driver;

	}
	public static WebDriver OpenApplication(WebDriver driver)throws Exception 
	{
		driver.get("http://webapp.qedgetech.com");
		driver.manage().deleteAllCookies();
		return driver;

	}
	public static void waitForElement(WebDriver driver,String locatortype,String locatorValue,String waittime)	
	{

		WebDriverWait mywait = new WebDriverWait(driver, Integer.parseInt(waittime));
		if(locatorValue.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
		}
		else
		{
			System.out.println("unable to Execute waitforelement method with "+locatortype);
		}

	}
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue) {
		if(locatortype.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).click();	
		}
		else if(locatortype.equalsIgnoreCase("xapth"))
		{
			driver.findElement(By.xpath(locatorvalue)).click();
		}
	}	
	public static void typeAction(WebDriver driver,String locatortype,String locatorvalue,String testdata)
	{
		if(locatortype.equalsIgnoreCase("id"))
		{  
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.id(locatorvalue)).sendKeys(Keys.ENTER);
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).click();
		}
		else if(locatortype.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).click();
		}
		else
		{
			System.out.println("Unable to Execute typeAction method");
		}
	}
	public static void captureData(WebDriver driver,String locatortype,String locatorvalue)throws Exception
	{
		String supplierdata="";

		if(locatortype.equalsIgnoreCase("id"))
		{
			supplierdata=driver.findElement(By.id(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("xpath"))
		{
			supplierdata=driver.findElement(By.xpath(locatorvalue)).getAttribute("value");
		}
		else if(locatortype.equalsIgnoreCase("id"))
		{
			supplierdata=driver.findElement(By.id(locatorvalue)).getAttribute("value");
		}
		else
		{
			System.out.println("Unbale to execute click captureData method");
		}
		FileWriter fw=new FileWriter(System.getProperty("user.dir")+"\\captureData/suppnumber.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(supplierdata);
		bw.flush();
		bw.close();
	}
	public static void tableValidation(WebDriver driver,String column)throws Exception
	{
		FileReader fr=new FileReader("./captureData/suppliernumber.txt");
		BufferedReader br=new BufferedReader(fr);
		String Exp_data=br.readLine();

		if(driver.findElement(By.xpath("//*[@id='psearch']")).isDisplayed()) {
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(Exp_data);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		}else { 
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
			driver.findElement(By.xpath("//*[@id='psearch']")).clear();
			driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(Exp_data);
			driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		}
		WebElement table=driver.findElement(By.xpath("//*[@id=tbl_a_supplierslist]"));
		java.util.List<WebElement> rows=(java.util.List<WebElement>) table.findElement(By.tagName("tr"));

		for(int i=1;i<rows.size();i++) {
			String act_data=driver.findElement(By.xpath("//*[@id=tbl_a_supplierslist]/tbody/tr["+i+"]/div/span/span")).getText();

			Assert.assertEquals(act_data, Exp_data);
			System.out.println(act_data+" "+Exp_data);
			break;

		}}}






