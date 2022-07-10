import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;

public class UpdateUserTest {

    @Test
    @Description("Try to update user data without authorization")
    @Step("Compare ER and OR, response code and body")
    public void updateUserWOAuth(){
        Response response = UpdateUser.UpdateUserWOAuth();
        response.then().assertThat().statusCode(401).body("success", equalTo(false));
    }

    @Test
    @Description("Try to update user data without authorization")
    @Step("Compare ER and OR, response code and body")
    public void updateUserWithAuth(){
        Response response = UpdateUser.updateUserWithAuth();
        response.then().assertThat().statusCode(200).body("success", equalTo(true));
    }
}