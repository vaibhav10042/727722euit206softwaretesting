package com.example;


import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class excercise2 
{
    WebDriver driver;
    WebDriverWait wait;
    @BeforeMethod
    public void setup() throws Exception{
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://demoblaze.com/");
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));

    }
    @Test(priority = 0)
    public void test1() throws Exception
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Laptops")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("MacBook air")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Add to cart")))).click();
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Cart")))).click();
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("/html/body/div[6]/div/div[1]/div/table/tbody/tr/td[2]"))));
        String str=element.getText();
        if(str.equalsIgnoreCase("MacBook air")){
            System.out.println("The selected product name and the displayed product name are same");
        }
        else{
            System.out.println("The selected product name and the displayed product name are not same");
        }
        
    }
    @SuppressWarnings("resource")
    @Test(priority = 1)
    public void test2() throws Exception{
        FileInputStream fs=new FileInputStream("D:\\Softwaretesting-XL Files\\demoblaze.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(fs);
        XSSFSheet login=wb.getSheet("login");
        XSSFRow row1=login.getRow(1);
        String username=row1.getCell(0).getStringCellValue();
        String password=row1.getCell(1).getStringCellValue();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.linkText("Log in")))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("loginusername")))).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated((By.id("loginpassword")))).sendKeys(password);
        driver.findElement((By.xpath("/html/body/div[3]/div/div/div[3]/button[2]"))).click();
        WebElement element=wait.until((ExpectedConditions.visibilityOfElementLocated((By.linkText("Welcome Testalpha")))));
        String str=element.getText();
        if(str.equalsIgnoreCase("Welcome Testalpha")){
            System.out.println("matched");
        }
        else{
            System.out.println("not matched");
        }



    }
    @AfterMethod
    public void endtest() throws Exception{
        Thread.sleep(5000);
        driver.quit();
    }
}