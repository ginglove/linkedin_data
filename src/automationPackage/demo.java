package automationPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import automationPackage.Commons.commons;

import java.io.IOException;
import java.util.Arrays;

public class demo {
    public static WebDriver driver=new ChromeDriver();
    public static commons common=new commons(driver);
    public static String prop_linkedInProfilePage = System.getProperty("user.dir") + "/src/automationPackage/Elements/linkedInProfilePage.properties";
    public static String prop_linkedInHomePage=System.getProperty("user.dir")+"/src/automationPackage/Elements/linkedInHomePage.properties";
    public static String filePath=System.getProperty("user.dir")+"/src/automationPackage/TestData/test.csv";
    public static void main(String[] args){
        String chromedriver_linux64=System.getProperty("user.dir")+"/Addons/chromedriver_linux64/chromedriver";
        System.setProperty("webdriver.chrome.driver", chromedriver_linux64);
        driver.manage().window().maximize();
        SignInLinkedInPage();
        OpenProfileInformation();
    }
    public static void SignInLinkedInPage() {
        common.openURL("https://www.linkedin.com/");
        //
        String e_account=common.Read_Properties_Files(prop_linkedInHomePage,"txt_Username");
        String e_password=common.Read_Properties_Files(prop_linkedInHomePage,"txt_Password");
        String e_btnSubmit=common.Read_Properties_Files(prop_linkedInHomePage,"btn_Accept");
        common.inputIntoElement("truonggiangle91@gmail.com",e_account);
        common.inputIntoElement("o12796303A@",e_password);
        common.clickToElement(e_btnSubmit);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void OpenProfileInformation() {
        String prop_linkedInProfilePage = System.getProperty("user.dir") + "/src/automationPackage/Elements/linkedInProfilePage.properties";
        String e_lnkProfile = common.Read_Properties_Files(prop_linkedInProfilePage, "lnk_Profile");
        String e_lnkProfileConnections = common.Read_Properties_Files(prop_linkedInProfilePage, "lnk_ProfileConnections");
        String e_pgNumberOfConnections = common.Read_Properties_Files(prop_linkedInProfilePage, "pg_NumberOfConnections");
        String e_btnNext = common.Read_Properties_Files(prop_linkedInProfilePage, "btn_Next");
        String e_lsNumberConnection=common.Read_Properties_Files(prop_linkedInProfilePage, "li_UserConnection");

        common.clickToElement(e_lnkProfile);
        common.clickToElement(e_lnkProfileConnections);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor)driver).executeScript("scroll(0,800)");
        int iNumberofList=common.numberOfListElement(e_pgNumberOfConnections);
        int iTotalPage= Integer.parseInt(common.getTextOfElement("//ul[@class='artdeco-pagination__pages artdeco-pagination__pages--number']//li["+iNumberofList+"]"));
        String url = driver.getCurrentUrl();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor)driver).executeScript("scroll(0,-800)");
        for(int i=1;i<iTotalPage+1;i++)
        {
            if(i==1){}
            else{
                url=url+"&page="+i+"";
            }
            int iTotalUser= common.numberOfListElement(e_lsNumberConnection);
            for (int z=1;z<iTotalUser+1;z++)
            {
                common.clickToElement("//ul[@class='reusable-search__entity-results-list list-style-none']//li["+z+"]//a[@class='app-aware-link']");
                getUserInformation();
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                common.openURL(url);
            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ((JavascriptExecutor)driver).executeScript("scroll(0,800)");
            common.clickToElement(e_btnNext);
            System.out.println("Click Next iteration : "+i);
        }
    }
    public static void getUserInformation(){
        String DisplayName="";
        String UserURL="";
        String UserMobile="";
        String UserEmail="";
        String UserBirthday="";
        String UserConnected="";
        String UserUniversityName="";
        String UserUniversityYear="";
        String e_lnkSeemore= common.Read_Properties_Files(prop_linkedInProfilePage,"lnk_SeeMore");
        String e_lnk_UserURL=common.Read_Properties_Files(prop_linkedInProfilePage, "lnk_UserURL");
        String e_H1UserName=common.Read_Properties_Files(prop_linkedInProfilePage, "h1_NameOfUser");
        String e_SpanUserMobile=common.Read_Properties_Files(prop_linkedInProfilePage, "span_UserMobile");
        String e_lnk_Email=common.Read_Properties_Files(prop_linkedInProfilePage,"lnk_UserEmail");
        String e_Span_BirthDay=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserBirthDay");
        String e_Span_Connected=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserConnected");
        String e_btn_Close=common.Read_Properties_Files(prop_linkedInProfilePage,"btn_Close");
        String e_span_Education_Name_Of_University=common.Read_Properties_Files(prop_linkedInProfilePage,"span_Education_Name_Of_University");
        String e_span_Education_Year=common.Read_Properties_Files(prop_linkedInProfilePage,"span_Education_Year");

        common.clickToElement(e_lnkSeemore);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DisplayName=common.getTextOfElement(e_H1UserName);
        UserURL= common.getTextOfElement(e_lnk_UserURL);
        if(common.isElementPresent(e_SpanUserMobile)==true)
        {
            UserMobile= common.getTextOfElement(e_SpanUserMobile);
        }
        else{}
        if(common.isElementPresent(e_lnk_Email)==true)
        {
            UserEmail=common.getTextOfElement(e_lnk_Email);
        }
        else{}
        if(common.isElementPresent(e_Span_BirthDay)==true)
        {
            UserBirthday=common.getTextOfElement(e_Span_BirthDay);
        }
        else{}
        if(common.isElementPresent(e_Span_Connected)==true)
        {
            UserConnected=common.getTextOfElement(e_Span_Connected);
        }
        else{}
        common.clickToElement(e_btn_Close);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ((JavascriptExecutor)driver).executeScript("scroll(0,1300)");
        ((JavascriptExecutor)driver).executeScript("scroll(0,1300)");
        if(common.isElementPresent(e_span_Education_Name_Of_University)==true)
        {
            UserUniversityName=common.getTextOfElement(e_span_Education_Name_Of_University);
        }
        else{}
        if(common.isElementPresent(e_span_Education_Year)==true)
        {
            UserUniversityYear=common.getTextOfElement(e_span_Education_Year);
            System.out.println(UserUniversityYear);
        }
        else{}
        String[] UserInfor=new String[]{DisplayName,UserURL,UserMobile,UserEmail,UserBirthday,UserConnected,UserUniversityName,UserUniversityYear};
        try {
            common.writeDataIntoCSV(filePath,UserInfor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(UserInfor));
    }
}
