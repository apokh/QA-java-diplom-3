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
    @DisplayName("Работают переходы к разделам: Булки, Соусы, Начинки")
    public void switchingSectionsConstructorIsWorksTest() throws InterruptedException {
        constructorPage.clickTabFillings();
        constructorPage.checkHeaderFillingsIsVisible();
        constructorPage.clickTabSauces();
        constructorPage.checkHeaderSaucesIsVisible();
        constructorPage.clickTabBun();
        constructorPage.checkHeaderBunIsVisible();
    }

    @After
    public void closeApp() {
        steps.sendDeleteRequestToDeleteUser(user);
        driver.close();
    }
}
