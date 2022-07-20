import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.CharSetUtils.delete;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest {


    public String accessToken;

    @Test
    @Description("Create user positive test")
    @Step("Compare ER and FR: status code and response body")
    public void createUserPositiveTest() {
        Response response = CreateUser.CreateNewUser(CreateUser.dynamicUserData);
        response.then().assertThat().statusCode(SC_OK).body("success", equalTo(true));
        System.out.println(response.body().asString());
        accessToken = response.then().extract().path("accessToken");
    }

    @Test
    @Description("Create the same user")
    @Step("Compare ER and FR: status code and response message")
    public void createSameUserTest() {
        CreateUser.CreateNewUser(CreateUser.dynamicUserData);
        Response response = CreateUser.CreateNewUser(CreateUser.dynamicUserData);
        response.then().assertThat().statusCode(SC_FORBIDDEN).body("success", equalTo(false));
        System.out.println(response.body().asString());
    }

    @Test
    @Description("Try to create user without name field")
    @Step("Compare ER and FR: status code and response message")
    public void createUserWONameField() {
        Response response = CreateUser.CreateNewUser(CreateUser.dynamicUserDataNoOneField);
        response.then().assertThat().statusCode(SC_FORBIDDEN).body("success", equalTo(false));
        System.out.println(response.body().asString());
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            CreateUser.delete(accessToken);
        }
        DetailsUser.removal(accessToken);

    }
}
