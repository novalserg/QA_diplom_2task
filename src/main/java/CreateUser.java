import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;

import static io.restassured.RestAssured.given;

public class CreateUser {

    static UserData userData = new UserData("Ivan724", "125421", "Ivan");
    static UserData userDataNoOneField = new UserData("Leha1875", "1578163", null);

    static final String URL = ("https://stellarburgers.nomoreparties.site/api/auth/register");

    @Step("Send POST to create unique User")
    public static Response CreateNewUser() {
        Response responseCreateUser = given()
                .header("Content-type", "application/json")
                .log().all()
                .and()
                .body(userData)
                .when()
                .post(URL);
        return responseCreateUser;
    }

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
                .body(userDataNoOneField)
                .when()
                .post(URL);
        return responseCreateUserWOField;
    }

}
