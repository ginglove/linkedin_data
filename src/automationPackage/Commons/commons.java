package automationPackage.Commons;
import org.apache.commons.configuration.ConfigurationException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.event.ConfigurationEvent;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.openqa.selenium.WebDriver;
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
    public String Read_Properties_Files(String filePath, String proKey) throws ConfigurationException {
        String value="";
        try {
            CompositeConfiguration config = new CompositeConfiguration();
            config.addConfiguration(new SystemConfiguration());
            config.addConfiguration(new PropertiesConfiguration(filePath));
            value = config.getString(proKey);
        }
        catch(ConfigurationException e) {
            System.out.println("Cannot get value on Properties file with " + e);
        }
        return value;
    }
}
