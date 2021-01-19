package automationPackage.Commons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.apache.commons.configuration2;
public class commons {
    public WebDriver driver;
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
    public void Read_Properties_Files(String filePath, String proKey) {
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        config.addConfiguration(new PropertiesConfiguration(filePath));

        String myValue = config.getString(proKey);
    }

}
