import api.StepsUser;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import pojo.request.UserRequest;

public class SwitchingSectionsConstructorIsWorksTest {
    ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);
    RegisterPage registerPage = new RegisterPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ConstructorPage constructorPage = new ConstructorPage(driver);
    StepsUser steps = new StepsUser();
    UserRequest user = steps.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() throws InterruptedException {
        registerPage.registerNewUser(user);
        loginPage.loginByUser(user);
    }

    @Test
    @DisplayName("Работает переход к разделу \"Булки\"")
    public void switchingSectionBunsConstructorIsWorksTest() throws InterruptedException {
        constructorPage.clickTabIngredientTypeName("Соусы");
        constructorPage.clickTabIngredientTypeName("Булки");
        constructorPage.checkHeaderIngredientTypeIsVisible("Булки");
    }

    @Test
    @DisplayName("Работает переход к разделу \"Соусы\"")
    public void switchingSectionSaucesConstructorIsWorksTest() throws InterruptedException {
        constructorPage.clickTabIngredientTypeName("Соусы");
        constructorPage.checkHeaderIngredientTypeIsVisible("Соусы");
    }

    @Test
    @DisplayName("Работает переход к разделу \"Начинки\"")
    public void switchingSectionFillingsConstructorIsWorksTest() throws InterruptedException {
        constructorPage.clickTabIngredientTypeName("Начинки");
        constructorPage.checkHeaderIngredientTypeIsVisible("Начинки");
    }

    @After
    public void closeApp() {
        steps.sendDeleteRequestToDeleteUser(user);
        driver.close();
    }
}
