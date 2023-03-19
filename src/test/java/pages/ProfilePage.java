package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pojo.request.UserRequest;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;
    //    лэйбл о возможности изменить данные пользователя
    private By labelAboutProfileChangeCan = By.xpath(".//p[text() = 'В этом разделе вы можете изменить свои персональные данные']");

    //    кнопка "Выход"
    private By goLogoutButton = By.xpath(".//button[text() = 'Выход']");

    public ProfilePage(WebDriver driver) {this.driver = driver;}

    @Step
    public void waitForElement(By by) {
        WebElement element = driver.findElement(by);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step
    public void clickGoLogoutButton() throws InterruptedException {
        waitForElement(goLogoutButton);
        driver.findElement(goLogoutButton).click();
        Thread.sleep(2000);
    }

    @Step
    public void checkUserName(UserRequest user) {
        Assert.assertEquals(user.getName(), driver.findElement(By.xpath(".//label[contains(@class, 'input__placeholder') and text() = 'Имя']/../input")).getAttribute("value"));
    }

    @Step
    public void checkUserLogin(UserRequest user) {
        Assert.assertEquals(user.getEmail(), driver.findElement(By.xpath(".//label[contains(@class, 'input__placeholder') and text() = 'Логин']/../input")).getAttribute("value"));
    }

    @Step
    public void checkUserDataByPage(UserRequest user) {
        checkUserName(user);
        checkUserLogin(user);
    }

    @Step
    public void checkLabelAboutProfileChangeCan() {
        driver.findElement(labelAboutProfileChangeCan).isDisplayed();
    }
}
