package com.example;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
public class AppTest {
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
    @BeforeMethod
    public void Testsetup () throws Exception {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\testreport\\index.html");
        report = new ExtentReports();
        report.attachReporter(sparkReporter);
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.get("http://dbankdemo.com/bank/login");
    }
    @SuppressWarnings("resource")
    @Test
    public void Test() throws Exception{
        FileInputStream fs = new FileInputStream("C:\\dbankexcel\\dbankexcel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet1 = workbook.getSheet("Login");
        XSSFRow ROW1 = sheet1.getRow(1);
        test = report.createTest("test started");
        String username = ROW1.getCell(0).getStringCellValue();
        String password = ROW1.getCell(1).getStringCellValue();
        Thread.sleep(3000);
        System.out.println(password);
        System.out.println(username);
        if(driver.getPageSource().contains("usename"))
            test.log(Status.PASS,"username is found");
        else{ 
            AppTest.screenshot(driver, "C:\\softwaretesting1\\extentreport\\src\\screenshot\\picture1.png");
            test.log(Status.FAIL, "username not found",MediaEntityBuilder.createScreenCaptureFromPath("C:\\softwaretesting1\\softwaretesting\\src\\screenshot\\picture1.png").build());
        
        }
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        Thread.sleep(8000);
    }
    public static void screenshot(WebDriver driver, String path) throws Exception
    {
        TakesScreenshot ss = ((TakesScreenshot) driver);
        File sourcefile = ss.getScreenshotAs(OutputType.FILE);
        File destinationfile = new File(path);
        FileUtils.copyFile(sourcefile, destinationfile);
    }

    @AfterMethod
    public void testquit(){
        report.flush();
        driver.quit();
    }
}
