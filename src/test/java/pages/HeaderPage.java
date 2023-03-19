package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderPage {
    private WebDriver driver;

    //    кнопка "Личный Кабинет"
    private By goProfileButton = By.xpath(".//p[text() = 'Личный Кабинет']");

    //    кнопка-лого "Stellar Burgers"
    private By goStellarBurgersLogo = By.xpath(".//div[contains(@class, 'logo')]");

    //    кнопка "Конструктор"
    private By goConstructorButton = By.xpath(".//a[@href = '/']/p[text() = 'Конструктор']");

    public HeaderPage(WebDriver driver) {this.driver = driver;}

    @Step
    public void waitForElement(By by) {
        WebElement element = driver.findElement(by);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step
    public void clickGoProfileButton() throws InterruptedException {
        waitForElement(goProfileButton);
        driver.findElement(goProfileButton).click();
        Thread.sleep(2000);
    }

    @Step
    public void clickGoStellarBurgersLogo() throws InterruptedException {
        waitForElement(goStellarBurgersLogo);
        driver.findElement(goStellarBurgersLogo).click();
        Thread.sleep(2000);
    }

    @Step
    public void clickGoConstructorButton() throws InterruptedException {
        waitForElement(goConstructorButton);
        driver.findElement(goConstructorButton).click();
        Thread.sleep(2000);
    }

}
