package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class ConstructorPage {
    private WebDriver driver;

    //    кнопка "Войти в аккаунт" на главной
    private By goLoginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");

    //    заголовок "Соберите бургер"
    private By headerConstructBurger = By.xpath(".//h1[text() = 'Соберите бургер']");

    //    блок с ингредиентами (булки, соусы, начинки)
    private By divIngredients = By.xpath(".//h2[text() = 'Булки']/..");


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
    public void clickTabIngredientTypeName(String ingredientTypeName) throws InterruptedException {
        By tabIngredientTypeName = By.xpath(".//span[text() = '" + ingredientTypeName + "']");
        driver.findElement(tabIngredientTypeName).click();
        Thread.sleep(2000);
    }

    @Step
    public void checkHeaderIngredientTypeIsVisible(String ingredientTypeName) {
        By headerIngredientTypeSection = By.xpath(".//h2[text() = '" + ingredientTypeName + "']");
        Point divLocation = driver.findElement(divIngredients).getLocation();
        Point sectionLocation = driver.findElement(headerIngredientTypeSection).getLocation();
        Assert.assertEquals(divLocation, sectionLocation);
    }
}
