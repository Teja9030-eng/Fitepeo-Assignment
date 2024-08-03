package Demo.FitPeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;

public class FitPeoTest {
    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout

        try {

            driver.get("https://fitpeo.com/revenue-calculator");


            WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiSlider-root")));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('value', '820'); arguments[0].dispatchEvent(new Event('input'));", slider);

            WebElement textField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".MuiTypography-root.MuiTypography-h3")));

            WebElement inputField = driver.findElement(By.cssSelector(".MuiInputBase-root input")); // Ensure correct selector
            inputField.clear();
            inputField.sendKeys("560");

            js.executeScript("arguments[0].setAttribute('value', '560'); arguments[0].dispatchEvent(new Event('input'));", slider);

            List<WebElement> checkboxes = driver.findElements(By.className("PrivateSwitchBase-input"));

            for (int i = 0; i < 3 && i < checkboxes.size(); i++) {
                WebElement checkbox = checkboxes.get(i);
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
            
            System.out.println("Test completed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
