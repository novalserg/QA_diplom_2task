import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    static LoginData loginData = new LoginData("alex@mail.ru", "123456");
    static UserData userChangedData = new UserData("john@mail.ru", "215421", "John Muller");

    static final String URLlogin = ("https://stellarburgers.nomoreparties.site/api/auth/login");
    static final String URLauth = ("https://stellarburgers.nomoreparties.site/api/auth/user");

    @Step("Try to update user data w/o authorization")
    public static Response UpdateUserWOAuth(){
        Response responseUpdUserWOAuth = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .when()
                .patch(URLauth);
        return responseUpdUserWOAuth;
    }

    public static Response getAccessToken() {
        Response accessToken = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body(loginData)
                .when()
                .post(URLlogin)
                .then()
                .extract()
                .path("accessToken");
        return accessToken;
    }
    public static Response updateUserWithAuth(){
        Response accessToken = getAccessToken();
        Response updateWithAuth = given()
                .headers(
                        "Authorization",
                        "Bearer " + accessToken,
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .log().all()
                .and()
                .body(userChangedData)
                .when()
                .patch(URLauth);
        return updateWithAuth;
    }
}