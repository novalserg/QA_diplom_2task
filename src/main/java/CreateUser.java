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
}
/*
    @Step("Send a POST to crate exsisting user")
    public static Response CreateSameUser() {
        CreateNewUser();
        Response responseCreateSameUser = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body(userData)
                .when()
                .post(URL);
        return responseCreateSameUser;
    }

    @Step("Send a POST without one field (name)")
    public static Response createUserWOField() {
        Response responseCreateUserWOField = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body()
                .when()
                .post(URL);
        return responseCreateUserWOField;
    }
    */