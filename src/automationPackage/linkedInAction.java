package automationPackage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import automationPackage.Commons.commons;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.Arrays;

public class linkedInAction {
    public static String prop_linkedInProfilePage = System.getProperty("user.dir") + "/src/automationPackage/Elements/linkedInProfilePage.properties";
    public static String prop_linkedInHomePage=System.getProperty("user.dir")+"/src/automationPackage/Elements/linkedInHomePage.properties";
    public static String filePath=System.getProperty("user.dir")+"/src/automationPackage/TestData/test.xlsx";
    public static String Account="";
    public static String Password="";
    public static String fileSheet="LinkedInData";
    public static void main(String[] args){

    }
    public static void LinkedInAction(commons common,String Account , String Password,String filePath,String fileSheet)
    {
        SignInLinkedInPage(common,Account,Password);
        OpenProfileInformation(common,filePath,fileSheet);
    }

    public static void SignInLinkedInPage(commons common,String Account,String Password) {
        common.openURL("https://www.linkedin.com/");
        //
        String e_account=common.Read_Properties_Files(prop_linkedInHomePage,"txt_Username");
        String e_password=common.Read_Properties_Files(prop_linkedInHomePage,"txt_Password");
        String e_btnSubmit=common.Read_Properties_Files(prop_linkedInHomePage,"btn_Accept");
        common.inputIntoElement(Account,e_account);
        common.inputIntoElement(Password,e_password);
        common.clickToElement(e_btnSubmit);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void OpenProfileInformation(commons common,String filePath,String fileSheet) {
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
        common.scrollToEndOfPage(800);
        int iNumberofList=common.numberOfListElement(e_pgNumberOfConnections);
        int iTotalPage= Integer.parseInt(common.getTextOfElement("//ul[@class='artdeco-pagination__pages artdeco-pagination__pages--number']//li["+iNumberofList+"]"));
        String url=common.getCurrentURL();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        common.scrollToEndOfPage(800);
        for(int i=1;i<iTotalPage+1;i++)
        {
            if(i==1){}
            else{
                url=url+"&page="+i+"";
            }
            int iTotalUser= common.numberOfListElement(e_lsNumberConnection);
            for (int z=1;z<iTotalUser+1;z++)
            {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                common.scrollToEndOfPage(-800);
                common.clickToElement("//ul[@class='reusable-search__entity-results-list list-style-none']//li["+z+"]//a[@class='app-aware-link']");
                getUserInformation(common,filePath,fileSheet);
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
            common.scrollToEndOfPage(800);
            common.clickToElement(e_btnNext);
            System.out.println("Click Next iteration : "+i);
        }
    }
    public static void getUserInformation(commons common,String filePath,String fileSheet){
        String DisplayName="";
        String UserURL="";
        String UserMobile="";
        String UserEmail="";
        String UserConnected="";
        String UserUniversityName="";
        String UserUniversityYear="";
        String UserCurrentJob="";
        String UserCurrentCompany="";
        String UserContactType="";
        String UserContactNick="";
        String UserSkill1="";
        String UserSkill2="";
        String UserSkill3="";

        String e_lnkSeemore= common.Read_Properties_Files(prop_linkedInProfilePage,"lnk_SeeMore");
        String e_lnk_UserURL=common.Read_Properties_Files(prop_linkedInProfilePage, "lnk_UserURL");
        String e_H1UserName=common.Read_Properties_Files(prop_linkedInProfilePage, "h1_NameOfUser");
        String e_SpanUserMobile=common.Read_Properties_Files(prop_linkedInProfilePage, "span_UserMobile");
        String e_lnk_Email=common.Read_Properties_Files(prop_linkedInProfilePage,"lnk_UserEmail");
        String e_Span_Connected=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserConnected");
        String e_Span_UserContactType=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserContactType");
        String e_Span_UserContactNick=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserContactNick");
        String e_btn_Close=common.Read_Properties_Files(prop_linkedInProfilePage,"btn_Close");

        String e_span_Education_Name_Of_University=common.Read_Properties_Files(prop_linkedInProfilePage,"span_Education_Name_Of_University");
        String e_span_Education_Year=common.Read_Properties_Files(prop_linkedInProfilePage,"span_Education_Year");
        String e_span_Current_Job=common.Read_Properties_Files(prop_linkedInProfilePage,"span_Current_Job");
        String e_span_Current_Company=common.Read_Properties_Files(prop_linkedInProfilePage,"span_Current_Company");

        String e_span_UserSkill1=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserSkill1");
        String e_span_UserSkill2=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserSkill2");
        String e_span_UserSkill3=common.Read_Properties_Files(prop_linkedInProfilePage,"span_UserSkill3");


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
        if(common.isElementPresent(e_Span_Connected)==true)
        {
            UserConnected=common.getTextOfElement(e_Span_Connected);
        }
        else{}
        if(common.isElementPresent(e_Span_UserContactType)==true)
        {
            UserContactType=common.getTextOfElement(e_Span_UserContactType);
        }
        else{}
        if(common.isElementPresent(e_Span_UserContactNick)==true)
        {
            UserContactNick=common.getTextOfElement(e_Span_UserContactNick);
        }
        else{}

        common.clickToElement(e_btn_Close);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        common.scrollToEndOfPage(1300);
        if(common.isElementPresent(e_span_Current_Job)==true)
        {
            UserCurrentJob=common.getTextOfElement(e_span_Current_Job);
        }
        else{}
        if(common.isElementPresent(e_span_Current_Company)==true)
        {
            UserCurrentCompany=common.getTextOfElement(e_span_Current_Company);
        }
        else{}
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        common.scrollToEndOfPage(1300);
        if(common.isElementPresent(e_span_Education_Name_Of_University)==true)
        {
            UserUniversityName=common.getTextOfElement(e_span_Education_Name_Of_University);
        }
        else{}
        if(common.isElementPresent(e_span_Education_Year)==true)
        {
            UserUniversityYear=common.getTextOfElement(e_span_Education_Year);
        }
        else{}

        common.scrollToEndOfPage(1300);
        if(common.isElementPresent(e_span_UserSkill1)==true)
        {
            UserSkill1=common.getTextOfElement(e_span_UserSkill1);
        }
        else{}
        if(common.isElementPresent(e_span_UserSkill2)==true)
        {
            UserSkill2=common.getTextOfElement(e_span_UserSkill2);
        }
        else{}
        if(common.isElementPresent(e_span_UserSkill3)==true)
        {
            UserSkill3=common.getTextOfElement(e_span_UserSkill3);
        }
        else{}

        String[] UserInfor=new String[]{DisplayName,UserCurrentJob,UserCurrentCompany,UserContactType,UserContactNick,UserEmail,UserURL,UserMobile,UserSkill1,UserSkill2,UserSkill3,UserUniversityName,UserUniversityYear,UserConnected};
        try {
            common.writeExcel(filePath,fileSheet,UserInfor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(UserInfor));
    }
}
