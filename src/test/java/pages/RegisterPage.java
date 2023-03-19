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

public class RegisterPage {
    private WebDriver driver;

    //    кнопка "Зарегистрироваться" внизу блока регистрации
    private By goRegisterButton = By.xpath(".//button[text() = 'Зарегистрироваться']");

    //    поле ввода "Имя"
    private By inputNameField = By.xpath(".//label[text() = 'Имя']/../input[@name = 'name']");

    //    поле ввода "Email"
    private By inputEmailField = By.xpath(".//label[text() = 'Email']/../input[@name = 'name']");

    //    поле ввода "Пароль"
    private By inputPasswordField = By.xpath(".//label[text() = 'Пароль']/../input[@name = 'Пароль']");

    //    лэйбл о некорректном значении в поле "Пароль"
    private By labelPasswordError = By.xpath(".//p[text() = 'Некорректный пароль']");

    public RegisterPage(WebDriver driver) {this.driver = driver;}

    @Step
    public void openRegisterPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step
    public void waitForElement(By by) {
        WebElement element = driver.findElement(by);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step
    public void enterName(String userName) {
        driver.findElement(inputNameField).sendKeys(userName);
    }

    @Step
    public void enterEmail(String userEmail) {
        driver.findElement(inputEmailField).sendKeys(userEmail);
    }

    @Step
    public void enterPassword(String userPassword) {
        driver.findElement(inputPasswordField).sendKeys(userPassword);
    }

    @Step
    public void clickGoRegisterButton() throws InterruptedException {
        waitForElement(goRegisterButton);
        driver.findElement(goRegisterButton).click();
        Thread.sleep(2000);
    }

    @Step
    public void checkLabelPasswordErrorIsDisplayed() {
        driver.findElement(labelPasswordError).isDisplayed();
    }

    @Step
    public void registerNewUser(UserRequest user) throws InterruptedException {
        openRegisterPage();
        enterEmail(user.getEmail());
        enterName(user.getName());
        enterPassword(user.getPassword());
        clickGoRegisterButton();
    }

}
