import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUserTest {

    @Test
    @Description("Create user positive test")
    @Step("Compare ER and FR: status code and response body")
    public void createUserPositiveTest(){
        Response response = CreateUser.CreateNewUser();
        response.then().assertThat().statusCode(200).body("success", equalTo(true));
        System.out.println(response.body().asString());
    }

    @Test
    @Description("Create the same user")
    @Step("Compare ER and FR: status code and response message")
    public void createSameUserTest(){
        Response response = CreateUser.CreateSameUser();
        response.then().assertThat().statusCode(403).body("success", equalTo(false));
        System.out.println(response.body().asString());
    }

    @Test
    @Description("Try to create user without name field")
    @Step("Compare ER and FR: status code and response message")
    public void createUserWONameField(){
        Response response = CreateUser.createUserWOField();
        response.then().assertThat().statusCode(403).body("success", equalTo(false));
        System.out.println(response.body().asString());
    }
}
