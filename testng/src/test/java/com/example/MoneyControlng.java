package com.example;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MoneyControlng 
{
    WebDriver driver;
    @BeforeTest
    public void Testsetup() throws Exception
    {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        driver.get("https://www.moneycontrol.com/");
        Thread.sleep(3000);
       
    }
        @Test(priority = 1)
        public void firstTest() throws Exception
        {
            driver.findElement(By.id("search_str")).sendKeys("Reliance Industries.",Keys.ENTER);
            driver.findElement(By.xpath("//*[@id=\"mc_mainWrapper\"]/div[3]/div[2]/div/table/tbody/tr[4]/td[1]/p/a/strong")).click();
            Thread.sleep(2000);
        }
        @Test(priority = 2)
        public void secondTest()throws Exception
        {
        driver.navigate().to("https://www.moneycontrol.com/mf/sipcalculator.php");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"ff_id\"]/option[3]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"im_id\"]/option[2]")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("invamt")).sendKeys("100000");
        Thread.sleep(2000);
        }
        @Test(priority = 3)
        public void ThirdTest()throws Exception
        {
         driver.findElement(By.id("stdt")).sendKeys("2021-08-02");
         Thread.sleep(1000);
         driver.findElement(By.id("endt")).sendKeys("2023-08-17",Keys.ENTER);
         Thread.sleep(1000);
         String dt = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
         String amt = driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]")).getText();
         System.out.println(dt);
         System.out.println(amt);
        }
    @AfterTest
    public void TestEnd()
    {
        driver.quit();
    }
}