import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.given;

public class CreateUser extends UrlMainPage {

    public static UserData dynamicUserData = new UserData(RandomStringUtils.randomAlphabetic(6) + "@mail.ru", RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(6));
    static UserData dynamicUserDataNoOneField = new UserData(RandomStringUtils.randomAlphabetic(6), RandomStringUtils.randomAlphabetic(7), null);

    static final String URL = ("https://stellarburgers.nomoreparties.site/api/auth/register");

    @Step("Send POST to create unique User")
    public static Response CreateNewUser(UserData dynamicUserData) {
        Response responseCreateUser = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body(CreateUser.dynamicUserData)
                .when()
                .post(URL);
        return responseCreateUser;
    }

    public static void delete(String accessToken) {
        Response responseDeleteUser = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body(accessToken)
                .when()
                .delete(UpdateUser.URLauth);

    }
}