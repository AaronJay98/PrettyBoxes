import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sctma\\OneDrive\\Desktop\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        driver.get("http://127.0.0.1:8080/signup");
        TimeUnit.SECONDS.sleep(1);
        
        //Testing if signup page works
        assert driver.getCurrentUrl() == "http://127.0.0.1:8080/signup";
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        
        //Adding information to form
        WebElement element = driver.findElement(By.id("email"));
        WebElement element2 = driver.findElement(By.id("password"));
        WebElement element3 = driver.findElement(By.id("firstName"));
        WebElement element4 = driver.findElement(By.id("lastName"));
        WebElement element5 = driver.findElement(By.id("phone"));
        WebElement element6 = driver.findElement(By.id("address"));
        WebElement element7 = driver.findElement(By.id("submit"));
        element.sendKeys("SeleniumTester");
        element2.sendKeys("password");
        element3.sendKeys("Barry");
        element4.sendKeys("Ng");
        element5.sendKeys("1234567");
        element6.sendKeys("123 Street");
        element7.click();
        
        //Should fail since SeleniumTester already exists
        assert driver.getCurrentUrl() == "http://127.0.0.1:8080/signup?error";
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        
        //Logging in
        driver.get("http://127.0.0.1:8080/login");
        TimeUnit.SECONDS.sleep(1);
        element = driver.findElement(By.id("User Name"));
        element2 = driver.findElement(By.id("Password"));
        element7 = driver.findElement(By.id("submit"));
        element.sendKeys("SeleniumTester");
        element2.sendKeys("password");
        element7.submit();
        
        //Should redirect to homepage
        assert driver.getCurrentUrl() == "http://127.0.0.1:8080/";
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        
        //Going to editor and saving default image
        driver.get("http://127.0.0.1:8080/editor");
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        element = driver.findElement(By.id("save"));
        element.click();
        
        //Should pass with alert
        assert driver.switchTo().alert().getText() == "Image Successfully Saved!";
        System.out.println("Alert Message: " + driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        
        //Should redirect to design page
        assert driver.getCurrentUrl() == "http://127.0.0.1:8080/design";
        System.out.println("Current URL is: " + driver.getCurrentUrl());
        
        //Adding design to cart
        element = driver.findElement(By.id("quantity0"));
        element2 = driver.findElement(By.id("button0"));
        element.sendKeys("20");
        element2.click();
        
        //Should have alert with Added to Cart! text
        assert driver.switchTo().alert().getText() == "Added to Cart!";
        System.out.println("Alert Message: " + driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        
        //Removing item from cart
        driver.get("http://127.0.0.1:8080/cart");
        element = driver.findElement(By.id("button0"));
        element.click();
        
        //Should have alert to say Removed from Cart
        assert driver.switchTo().alert().getText() == "Removed from Cart";
        System.out.println("Alert Message: " + driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        
        
        driver.close();
    }
}
