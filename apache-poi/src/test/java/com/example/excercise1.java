package com.example;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class excercise1 
{
    WebDriver driver;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
    }
    @SuppressWarnings("resource")
    @BeforeMethod
    public void openpage() throws Exception{
        driver=new ChromeDriver();
        driver.get("http://dbankdemo.com/bank/login");
        driver.navigate().refresh();
        FileInputStream fs=new FileInputStream("D:\\Softwaretesting-XL Files\\DigitalBank.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(fs);
        XSSFSheet login=wb.getSheet("login");
        XSSFRow row1=login.getRow(1);
        String username=row1.getCell(0).getStringCellValue();
        String password=row1.getCell(1).getStringCellValue();
        Thread.sleep(3000);
        driver.findElement((By.id("username"))).sendKeys(username);
        Thread.sleep(3000);
        driver.findElement((By.id("password"))).sendKeys(password);
        Thread.sleep(3000);
        driver.findElement((By.id("submit"))).click();
    }
    
    @Test(priority = 0)
    public void test1() throws Exception
    {
        String currenturl=driver.getCurrentUrl().toString();
        if(currenturl.contains("home")){
            System.out.println("login successful");
        }
        else{
            System.out.println("error in login");
        }
        
    }
    @Test(priority = 1)
    public void test2() throws Exception{
        Thread.sleep(3000);
        driver.findElement((By.linkText("Deposit"))).click();
        Thread.sleep(3000);
        Select select=new Select(driver.findElement((By.id("selectedAccount"))));
        Thread.sleep(3000);
        select.selectByVisibleText("Individual Checking(standard checking) (Savings)");
        Thread.sleep(3000);
        driver.findElement((By.id("amount"))).sendKeys("5000");
        Thread.sleep(3000);
        driver.findElement((By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]"))).click();
        Thread.sleep(3000);
        WebElement Table=driver.findElement((By.xpath("//*[@id='transactionTable']")));
        Thread.sleep(3000);
        WebElement row1=Table.findElement((By.xpath("//*[@id='transactionTable']/tbody/tr[1]")));
        Thread.sleep(3000);
        WebElement cell=row1.findElement((By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")));
        String s=cell.getText();
        if(s.equalsIgnoreCase("$5000.00")){
            System.out.println("Deposited amount is present in the table");
            
        }
        else{
            System.out.println("Deposited amount is not present in the tabel");
        }
    }
    @Test(priority = 2)
    public void test3() throws Exception{
        Thread.sleep(3000);
        driver.findElement((By.linkText("Withdraw"))).click();
        Thread.sleep(3000);
        Select select=new Select(driver.findElement((By.id("selectedAccount"))));
        Thread.sleep(3000);
        select.selectByVisibleText("Individual Checking(standard checking) (Savings)");
        Thread.sleep(3000);
        driver.findElement((By.id("amount"))).sendKeys("3000");
        Thread.sleep(3000);
        driver.findElement((By.xpath("////[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]"))).click();
        Thread.sleep(3000);
        WebElement Table=driver.findElement((By.xpath("//*[@id='transactionTable']")));
        Thread.sleep(3000);
        WebElement row1=Table.findElement((By.xpath("//*[@id='transactionTable']/tbody/tr[1]")));
        Thread.sleep(3000);
        WebElement cell=row1.findElement((By.xpath("//*[@id='transactionTable']/tbody/tr[1]/td[4]")));
        Thread.sleep(3000);
        String s=cell.getText();
        if(s.equalsIgnoreCase("$-3000.00")){
            System.out.println("Deposited amount is present in the table");
            
        }
        else{
            System.out.println("Deposited amount is not present in the tabel");
        }
    }
    @AfterMethod
    public void closepage(){
        driver.quit();
    }
    @AfterTest
    public void endtest(){
        driver.quit();
    }
}