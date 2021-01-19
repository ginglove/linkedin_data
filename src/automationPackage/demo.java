package automationPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import automationPackage.Commons.commons;
public class demo {
    public commons common=new commons();

    public static void main(String[] args){
//        FirefoxOptions options = new FirefoxOptions();
//        options.setHeadless(true);
//        FirefoxDriver driver=new FirefoxDriver(options);
        OpenFireFoxAndRun();
    }
    public static void OpenFireFoxAndRun(){
        FirefoxDriver driver=new FirefoxDriver();
        common.openURL("http://demo.guru99.com/");
        WebElement element=driver.findElement(By.xpath("//input[@name='emailid']"));
        element.sendKeys("abc@gmail.com");
        WebElement button=driver.findElement(By.xpath("//input[@name='btnLogin']"));
        button.click();
        driver.close();
    }
}
