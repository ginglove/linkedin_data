package sample;
import automationPackage.linkedInAction;
import automationPackage.Commons.commons;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import automationPackage.Commons.commons;
import org.openqa.selenium.chrome.ChromeOptions;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Controller implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtAccount;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtFilePath;
    @FXML
    private TextField txtFileSheet;
    @FXML
    private Label  labelSubmitButton;

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){

    }

    public void pressButton(ActionEvent event)
    {
        try {
            if (txtAccount.getText().isBlank() == true) {
                labelSubmitButton.setText("Please Input Account");
            } else if (txtPassword.getText().isBlank() == true) {
                labelSubmitButton.setText("Please Input Password");
            } else if (txtFilePath.getText().isBlank() == true) {
                labelSubmitButton.setText("Please Input Excel File Path");
            } else if (txtFileSheet.getText().isBlank() == true) {
                labelSubmitButton.setText("Please Input Excel File Sheet Name");
            } else if (txtAccount.getText().isBlank() == true && txtPassword.getText().isBlank() == true && txtFilePath.getText().isBlank() == true && txtFileSheet.getText().isBlank() == true) {
                labelSubmitButton.setText("Please Input All Value");
            } else {
                String chromedriver_linux64=System.getProperty("user.dir")+"/Addons/chromedriver_linux64/chromedriver";
                System.setProperty("webdriver.chrome.driver", chromedriver_linux64);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                WebDriver driver = new ChromeDriver(options);
                commons common =new commons(driver);
                linkedInAction test=new linkedInAction();
                String Account =txtAccount.getText();
                String Password=txtPassword.getText();
                String filePath=txtFilePath.getText();
                String fileSheet=txtFileSheet.getText();
                linkedInAction.SignInLinkedInPage(common, Account, Password);
               // linkedInAction.OpenProfileInformation(common);
                linkedInAction.OpenUserNetwork(common,filePath,fileSheet);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            Stage stage=(Stage) btnCancel.getScene().getWindow();
            stage.close();
        }
//        String chromedriver_linux64=System.getProperty("user.dir")+"/Addons/chromedriver_linux64/chromedriver";
//        System.setProperty("webdriver.chrome.driver", chromedriver_linux64);
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--window-size=1920,1080");
//        WebDriver driver = new ChromeDriver(options);
//        commons common =new commons(driver);
//        linkedInAction test=new linkedInAction();
//        String Account =txtAccount.getText();
//        String Password=txtPassword.getText();
//        String filePath=txtFilePath.getText();
//        String fileSheet=txtFileSheet.getText();
//        linkedInAction.SignInLinkedInPage(common, Account, Password);
//        linkedInAction.OpenUserNetwork(common,filePath,fileSheet);
    }
    public void cancelButtonOnAction(ActionEvent event){
        Stage stage=(Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public String getTxtFilePath(){
        String filePath="";
        filePath=txtFilePath.getText();
        return filePath;
    }
    public String getTxtFileSheetName(){
        String fileSheet="";
        fileSheet=txtFilePath.getText();
        return fileSheet;
    }

    public void logicScreenForm()
    {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("logicScreen.fxml"));
            Stage logicStage=new Stage();
            logicStage.initStyle(StageStyle.UNDECORATED);
            logicStage.setScene(new Scene(root, 700, 500));
            logicStage.show();

        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
