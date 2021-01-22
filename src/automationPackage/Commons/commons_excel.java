package automationPackage.Commons;

import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
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

public class commons_excel {

    public Integer getTotalRowOfExcel(String fileName,String sheetName) throws IOException{
        //Create an object of File class to open xlsx file
        int rowCount=0;
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
        rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
        return rowCount;
    }
}
