package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private WebDriver driver;

    //    кнопка "Войти" внизу блока восстановления
    private By goLoginButton = By.xpath(".//a[text() = 'Войти']");

    public RestorePasswordPage(WebDriver driver) {this.driver = driver;}

    @Step
    public void clickGoLoginButton() throws InterruptedException {
        driver.findElement(goLoginButton).click();
        Thread.sleep(2000);
    }
}
