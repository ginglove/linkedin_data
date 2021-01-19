package automationPackage;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import automationPackage.Commons.commons;
public class demo {
    public  static FirefoxDriver driver=new FirefoxDriver();
    public static commons common=new commons(driver);

    public static void main(String[] args){
//        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(true);
//        FirefoxDriver driver=new FirefoxDriver(options);
        try {
            OpenFireFoxAndRun();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
    public static void OpenFireFoxAndRun() throws ConfigurationException {
        common.openURL("https://www.linkedin.com/");
        String e_account=common.Read_Properties_Files("./Elements/linkedInHomePage.properties","txt_Username");
        WebElement element=driver.findElement(By.xpath(e_account));
        element.sendKeys("abc@gmail.com");
        driver.close();
    }
}
