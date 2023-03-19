package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import pojo.request.UserRequest;

import static io.restassured.RestAssured.given;

public class BaseSteps {
    public BaseApi baseApi = new BaseApi();
    private final String USER_LOGIN_PATH = "/api/auth/login";

    @Step
//    Выполнить POST-запрос на авторизацию пользователем
    public Response sendPostRequestToLoginAsUser(UserRequest user) {
        return given().spec(baseApi.baseSpec())
                .body(user)
                .when()
                .post(USER_LOGIN_PATH);
    }
}