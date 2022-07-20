import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class LoginUser {

    static LoginData loginData = new LoginData("alex@mail.ru", "123456");
    static LoginData loginDataWrong = new LoginData("leha@mai.ru", "1578167");

    static final String URL = ("https://stellarburgers.nomoreparties.site/api/auth/register");

    @Step("Try to login with property data")
    public static Response loginUser(){
        Response responseLoginUser = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body(loginData)
                .when()
                .post(URL);
        return responseLoginUser;
    }

    @Step("Try to login as unexisting user")
    public static Response loginUserWrongData() {
        Response responseLoginWrongUser=given()
            .header("Content-type", "application/json")
            .log().all()
            .and()
            .body(loginDataWrong)
            .when()
            .post(URL);
        return responseLoginWrongUser;
    }
}
