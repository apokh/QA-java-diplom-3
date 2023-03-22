package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pojo.request.UserRequest;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;

    //    заголовок "Вход"
    private By headerLoginPage = By.xpath(".//h2[text() = 'Вход']");

    //    кнопка "Войти"
    private By goLoginButton = By.xpath(".//button[text() = 'Войти']");

    //    кнопка "Восстановить пароль"
    private By goRestorePasswordButton = By.xpath(".//a[text() = 'Восстановить пароль']");

    //    поле ввода "Email"
    private By inputEmailField = By.xpath(".//label[text() = 'Email']/../input[@name = 'name']");

    //    поле ввода "Пароль"
    private By inputPasswordField = By.xpath(".//label[text() = 'Пароль']/../input[@name = 'Пароль']");

    public LoginPage(WebDriver driver) {this.driver = driver;}

    @Step
    public void waitForElement(By by) {
        WebElement element = driver.findElement(by);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step
    public void openLoginPage() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Step
    public void checkDoesLoginPage() {
        waitForElement(headerLoginPage);
        driver.findElement(headerLoginPage).isDisplayed();
    }
    @Step
    public void enterEmail(String userEmail) {
        waitForElement(inputEmailField);
        driver.findElement(inputEmailField).sendKeys(userEmail);
    }

    @Step
    public void enterPassword(String userPassword) {
        waitForElement(inputPasswordField);
        driver.findElement(inputPasswordField).sendKeys(userPassword);
    }

    @Step
    public void clickGoLoginButton() throws InterruptedException {
        waitForElement(goLoginButton);
        driver.findElement(goLoginButton).click();
        Thread.sleep(2000);
    }

    @Step
    public void clickGoRestorePasswordButton() throws InterruptedException {
        waitForElement(goRestorePasswordButton);
        driver.findElement(goRestorePasswordButton).click();
        Thread.sleep(2000);
    }

    @Step
    public void loginByUser(UserRequest user) throws InterruptedException {
        openLoginPage();
        enterEmail(user.getEmail());
        enterPassword(user.getPassword());
        clickGoLoginButton();
    }
}
