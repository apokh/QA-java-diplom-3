import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import pojo.request.UserRequest;

public class LogoutSuccessTest {
    ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);
    HeaderPage headerPage = new HeaderPage(driver);
    RegisterPage registerPage = new RegisterPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    ProfilePage profilePage = new ProfilePage(driver);
    StepsUser steps = new StepsUser();
    UserRequest user = steps.prepareSimpleUserDataToCreate();

    @Before
    public void prepareTestData() throws InterruptedException {
        registerPage.registerNewUser(user);
        loginPage.loginByUser(user);
        headerPage.clickGoProfileButton();
        profilePage.checkLabelAboutProfileChangeCan();
    }

    @Test
    @DisplayName("Выход по кнопке \"Выйти\" в личном кабинете работает")
    @Description(" - по клику на кнопке \"Выйти\" происходит переход на страницу авторизации\n" +
            " - после выхода клик по кнопке \"Личный кабинет\" переводит на страницу авторизации, а не в профиль")
    public void logoutSuccessTest() throws InterruptedException {
        profilePage.clickGoLogoutButton();
        loginPage.checkDoesLoginPage();
        headerPage.clickGoProfileButton();
        loginPage.checkDoesLoginPage();
    }

    @After
    public void closeApp() {
        steps.sendDeleteRequestToDeleteUser(user);
        driver.close();
    }
}
