package automationPackage.Commons;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import org.openqa.selenium.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.opencsv.CSVWriter;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

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
    public void writeExcel(String fileName,String sheetName,String[] dataToWrite) throws IOException{
        //Create an object of File class to open xlsx file
        File file =    new File(fileName);

        //Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook newWorkBook = null;
        //Find the file extension by splitting  file name in substring and getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        //Check condition if the file is xlsx file
        if(fileExtensionName.equals(".xlsx")){
            //If it is xlsx file then create object of XSSFWorkbook class
            newWorkBook = new XSSFWorkbook(inputStream);
        }
        //Check condition if the file is xls file
        else if(fileExtensionName.equals(".xls")){
            //If it is xls file then create object of XSSFWorkbook class
            newWorkBook = new HSSFWorkbook(inputStream);
        }
        //Read excel sheet by sheet name
        Sheet sheet = newWorkBook.getSheet(sheetName);
        //Get the current count of rows in excel file
        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        //Get the first row from the sheet
        Row row = sheet.getRow(0);
        //Create a new row and append it at last of sheet
        Row newRow = sheet.createRow(rowCount+1);
        //Create a loop over the cell of newly created Row
        for(int j = 0; j < row.getLastCellNum(); j++){
            //Fill data in row
            Cell cell = newRow.createCell(j);
            cell.setCellValue(dataToWrite[j]);
        }
        //Close input stream
        inputStream.close();
        //Create an object of FileOutputStream class to create write data in excel file
        FileOutputStream outputStream = new FileOutputStream(file);
        //write data in the excel file
        newWorkBook.write(outputStream);
        //close output stream
        outputStream.close();
    }
}
