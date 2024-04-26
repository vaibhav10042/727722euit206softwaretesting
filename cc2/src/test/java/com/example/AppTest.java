package com.example;
import java.io.FileInputStream;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
public class AppTest 
{
    WebDriver driver;
    ExtentReports report;
    ExtentTest test;
   @BeforeTest
   public void BeforeTest()
   {
    ExtentSparkReporter sparkReporter = new ExtentSparkReporter("C:\\testreport\\index1.html");
    report = new ExtentReports();
    report.attachReporter(sparkReporter);
         driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.get("https://www.barnesandnoble.com/");
   }
   @SuppressWarnings("resource")
    @Test(priority = 0)
    public void Testcase1() throws Exception
    {
        
        driver.findElement(By.linkText("All")).click();
        driver.findElement(By.linkText("Books")).click();
        Thread.sleep(3000);
        FileInputStream fs = new FileInputStream("C:\\dbankexcel\\dbankexcel.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheet("cc2");
        XSSFRow row = sheet.getRow(0);
        String book_name = row.getCell(0).getStringCellValue();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(book_name);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        Thread.sleep(5000);
        test = report.createTest("testing Chetan Bhagat");
        if(driver.getPageSource().contains("Chetan Bhagat"))
            test.log(Status.PASS, "Chetan Bhagat is present ");
        else
            test.log(Status.FAIL, "Chetan Bhagat is not present");
    }
    @Test(priority = 1)
    public void test2() throws InterruptedException
    {
        WebElement audio = driver.findElement(By.linkText("Audiobooks"));
        Thread.sleep(2000);
        Actions act = new Actions(driver);
        act.moveToElement(audio).perform();
        driver.findElement(By.linkText("Audiobooks Top 100")).click();
        Thread.sleep(4000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
        driver.findElement(By.linkText("Funny Story")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,500)");
        driver.findElement(By.xpath("//*[@id=\"commerce-zone\"]/div[2]/ul/li[2]/div/div/label/span")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"find-radio-checked\"]/div[1]/form/input[5]")).click();
        Thread.sleep(2000);
        WebElement cart = driver.findElement(By.xpath("//*[@id='add-to-bag-main']/div[1]"));
        String add = cart.getText();
        if (add.equals("Item Successfully Added To Your cart")) 
        test.info("TestCase 2 Executed Successfully!");
        else
            test.info("Error Occurred!");
        Thread.sleep(2000);
    }
     @Test(priority = 2)
    public void Test3() throws Exception{
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("windows.scrollBy(0,80)");
        driver.findElement(By.xpath("//*[@id='footer']/div/dd/div/div/div[1]/div/a[5]")).click();
        Thread.sleep(2000);
        js.executeScript("windows.scrollBy(0,100)");
        driver.findElement(By.xpath("//*[@id='rewards-modal-link']"));
        WebElement check=driver.findElement(By.xpath("//*[@id=\"dialog-title\"]"));
        Assert.assertTrue(check.getText().contains("Sign in or Create an Account"), "Sign in first!!");
        test.info("TestCase 3 passed Successfully!");
    }
    @AfterTest
    public void AfterTest(){
        driver.quit();
    }
}
