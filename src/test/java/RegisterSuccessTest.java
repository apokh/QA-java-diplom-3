import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import pojo.request.UserRequest;

public class RegisterSuccessTest {
    ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);
    HeaderPage headerPage = new HeaderPage(driver);
    RegisterPage registerPage = new RegisterPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ConstructorPage constructorPage = new ConstructorPage(driver);
    ProfilePage profilePage = new ProfilePage(driver);
    StepsUser steps = new StepsUser();
    UserRequest user = steps.prepareSimpleUserDataToCreate();

    @Test
    @DisplayName("Создание нового пользователя с последующей авторизацией")
    @Description(" - новый пользователь может зарегистрироваться на сайте \n" +
            " - новый пользователь успешно авторизовался после регистрации")
    public void registerSuccessTest() throws InterruptedException {
        registerPage.registerNewUser(user);
        loginPage.checkDoesLoginPage();
        loginPage.loginByUser(user);
        constructorPage.checkHeaderConstructBurgerIsDisplayed();
        headerPage.clickGoProfileButton();
        profilePage.checkUserName(user);
        profilePage.checkUserLogin(user);
    }

    @After
    public void closeApp() {
        steps.sendDeleteRequestToDeleteUser(user);
        driver.close();
    }
}
