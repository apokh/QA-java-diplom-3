package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.request.UserRequest;
import pojo.response.UserCreateSuccess;

import static io.restassured.RestAssured.given;

public class StepsUser extends BaseSteps {
    private final String USER_DELETE_PATH = "/api/auth/user";

    @Step
//    подготовить простые данные пользователя
    public UserRequest prepareSimpleUserDataToCreate() {
        return new UserRequest("test-data-auto-apokh-15@yandex.ru", "123456", "auto-apokh-15");
    }

    @Step
//    Выполнить DELETE-запрос на удаление пользователя
    public Response sendDeleteRequestToDeleteUser(UserRequest user) {
        Response response = sendPostRequestToLoginAsUser(user);
        String userToken = response.as(UserCreateSuccess.class).getAccessToken().replaceAll("Bearer ", "");
        return given().spec(baseApi.baseSpec())
                .auth()
                .oauth2(userToken)
                .delete(USER_DELETE_PATH);
    }
}