import api.StepsUser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.*;
import pojo.request.UserRequest;

public class RegisterWithShortPasswordFailureTest {
    ChromeOptions options = new ChromeOptions().addArguments("--remote-allow-origins=*");
    WebDriver driver = new ChromeDriver(options);
    RegisterPage registerPage = new RegisterPage(driver);
    StepsUser steps = new StepsUser();
    UserRequest user = steps.prepareSimpleUserDataToCreate();

    @Test
    @DisplayName("Нельзя зарегистрировать нового пользователя с паролем меньше 6 символов")
    @Description(" - пароль не может быть меньше 6 символов \n - под полем \"Пароль\" выводится текст \"Некорректный пароль\"")
    public void registerWithShortPasswordFailureTest() throws InterruptedException {
        registerPage.openRegisterPage();
        registerPage.enterEmail(user.getEmail());
        registerPage.enterName(user.getName());
        registerPage.enterPassword("12345");
        registerPage.clickGoRegisterButton();
        registerPage.checkLabelPasswordErrorIsDisplayed();
        driver.close();
    }
}
