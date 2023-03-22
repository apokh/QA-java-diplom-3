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

public class LoginDifferentWaysTest {
    ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);
    HeaderPage headerPage = new HeaderPage(driver);
    RegisterPage registerPage = new RegisterPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ProfilePage profilePage = new ProfilePage(driver);
    ConstructorPage constructorPage = new ConstructorPage(driver);
    RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
    StepsUser steps = new StepsUser();
    UserRequest user = steps.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() throws InterruptedException {
        registerPage.registerNewUser(user);
    }

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной")
    public void loginByConstructorPageLoginButtonSuccessTest() throws InterruptedException {
        headerPage.clickGoStellarBurgersLogo();
        constructorPage.clickGoLoginButton();
        loginPage.loginByUser(user);
        headerPage.clickGoProfileButton();
        profilePage.checkUserDataByPage(user);
    }

    @Test
    @DisplayName("Вход через личный кабинет")
    public void loginByHeaderPageProfileButtonSuccessTest() throws InterruptedException {
        headerPage.clickGoProfileButton();
        loginPage.loginByUser(user);
        headerPage.clickGoProfileButton();
        profilePage.checkUserDataByPage(user);
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginAfterRegistrationSuccessTest() throws InterruptedException {
        loginPage.enterEmail(user.getEmail());
        loginPage.enterPassword(user.getPassword());
        loginPage.clickGoLoginButton();
        headerPage.clickGoProfileButton();
        profilePage.checkUserDataByPage(user);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginBeforeRestorePasswordSuccessTest() throws InterruptedException {
        loginPage.clickGoRestorePasswordButton();
        restorePasswordPage.clickGoLoginButton();
        loginPage.loginByUser(user);
        headerPage.clickGoProfileButton();
        profilePage.checkUserDataByPage(user);
    }

    @After
    public void closeApp() {
        steps.sendDeleteRequestToDeleteUser(user);
        driver.close();
    }
}
