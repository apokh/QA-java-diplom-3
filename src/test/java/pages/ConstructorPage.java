package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private WebDriver driver;

    //    кнопка "Войти в аккаунт" на главной
    private By goLoginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");

    //    заголовок "Соберите бургер"
    private By headerConstructBurger = By.xpath(".//h1[text() = 'Соберите бургер']");

    //    кнопка "Булки" на главной
    private By tabBun = By.xpath(".//span[text() = 'Булки']");

    //    кнопка "Соусы" на главной
    private By tabSauces = By.xpath(".//span[text() = 'Соусы']");

    //    кнопка "Начинки" на главной
    private By tabFillings = By.xpath(".//span[text() = 'Начинки']");

    //    заголовок раздела "Булки" на главной
    private By headerBun = By.xpath(".//h2[text() = 'Булки']");

    //    заголовок раздела "Соусы" на главной
    private By headerSauces = By.xpath(".//h2[text() = 'Соусы']");

    //    заголовок раздела "Начинки" на главной
    private By headerFillings = By.xpath(".//h2[text() = 'Начинки']");


    public ConstructorPage(WebDriver driver) {this.driver = driver;}

    @Step
    public void checkHeaderConstructBurgerIsDisplayed() {
        driver.findElement(headerConstructBurger).isDisplayed();
    }

    @Step
    public void clickGoLoginButton() throws InterruptedException {
        driver.findElement(goLoginButton).click();
        Thread.sleep(2000);
    }

    @Step
    public void clickTabBun() throws InterruptedException {
        driver.findElement(tabBun).click();
        Thread.sleep(2000);
    }

    @Step
    public void clickTabSauces() throws InterruptedException {
        driver.findElement(tabSauces).click();
        Thread.sleep(2000);
    }

    @Step
    public void clickTabFillings() throws InterruptedException {
        driver.findElement(tabFillings).click();
        Thread.sleep(2000);
    }

    @Step
    public void checkHeaderBunIsVisible() {
        driver.findElement(headerBun).isDisplayed();
    }

    @Step
    public void checkHeaderSaucesIsVisible() {
        driver.findElement(headerSauces).isDisplayed();
    }

    @Step
    public void checkHeaderFillingsIsVisible() {
        driver.findElement(headerFillings).isDisplayed();
    }
}
