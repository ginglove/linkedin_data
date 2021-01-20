package automationPackage.Commons;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.opencsv.CSVWriter;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class commons {
    public WebDriver driver;
    public commons(WebDriver driver) {
        this.driver = driver;
    }
    public void openURL(String URL)
    {
        try{
            System.out.println("Open Browser with URL "+URL);
            driver.get(URL);
        }
        catch(Exception e)
        {
            System.err.println("Cannot open URL with error "+e);
        }
    }
    public void inputIntoElement(String txtInput,String eXpath)
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(eXpath)));
            driver.findElement(By.xpath(eXpath)).sendKeys(txtInput);
            System.out.println("Input into "+eXpath +"with string "+txtInput+ " successfully");
        }
        catch (ElementNotVisibleException e){
            System.err.println("No Element is Visible: "+e);
        }
        catch(Exception e)
        {
            System.err.println("Cannot input into Element due to: "+e);
        }
    }
    public void clickToElement(String eXpath)
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(eXpath)));
            driver.findElement(By.xpath(eXpath)).click();
            System.out.println("Click "+eXpath+" successfully");
        }
        catch (ElementNotVisibleException e){
            System.err.println("No Element is Visible: "+e);
        }
        catch(Exception e)
        {
            System.err.println("Cannot input into Element due to: "+e);
        }
    }
    public String Read_Properties_Files(String filePath, String proKey){
        String value="";
        try (InputStream input = new FileInputStream(filePath)) {
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            value=prop.getProperty(proKey);
            // get the property value and print it out
            System.out.println(prop.getProperty(value));

        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Cannot get properties with : "+ex);
        }
        return value;
    }
    public int numberOfListElement(String eXpath){
        int iNumberOfListElement=0;
        try{
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(eXpath)));
            List<WebElement> ls_Element=driver.findElements(By.xpath(eXpath));
            iNumberOfListElement=ls_Element.size();
            System.out.println("Number of list "+eXpath+" is "+iNumberOfListElement);
        }
        catch (ElementNotVisibleException e){
            System.err.println("No Element is Visible: "+e);
        }
        catch(Exception e)
        {
            System.err.println("Cannot input into Element due to: "+e);
        }
        return iNumberOfListElement;
    }

    public String getTextOfElement(String eXpath){
        String strTextOfElement="";
        try{
            WebDriverWait wait = new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(eXpath)));
            strTextOfElement=driver.findElement(By.xpath(eXpath)).getText();
        }
        catch (ElementNotVisibleException e){
            System.err.println("No Element is Visible: "+e);
        }
        catch(Exception e)
        {
            System.err.println("Cannot input into Element due to: "+e);
        }
        return strTextOfElement;
    }
    public void scrollToEndOfPage()
    {
        try{
            ((JavascriptExecutor)driver).executeScript("scroll(0,800)");
        }
        catch(Exception e)
        {
            System.err.println("Cannot Scroll due to : "+e);
        }
    }
    public boolean isElementIsVisible(String eXpath)
    {
        boolean isVisible=false;
        isVisible=driver.findElement(By.xpath(eXpath)).isDisplayed();
        if(isVisible==true)
        {
            System.out.println("Element "+eXpath+ " is Visible");
        }
        else{
            System.out.println("Element "+eXpath+ " is not Visible");
        }
        return  isVisible;
    }
    public boolean isElementPresent(String eXpath) {
        try {
            driver.findElement(By.xpath(eXpath));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    public void writeDataIntoCSV(String filePath,String[] dataInput) throws IOException {
        try {
            // create a write
            Writer writer = Files.newBufferedWriter(Paths.get(filePath));

            // header record
            // create a csv writer
            ICSVWriter csvWriter = new CSVWriterBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withEscapeChar(CSVWriter.DEFAULT_ESCAPE_CHARACTER)
                    .withLineEnd(CSVWriter.DEFAULT_LINE_END)
                    .build();

            // write header record
            // write data records
            csvWriter.writeNext(dataInput);

            // close writers
            csvWriter.close();
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
