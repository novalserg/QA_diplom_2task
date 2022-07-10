import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginUserTest{

        @Test
        @Description("Login existing user, proper data")
        @Step("Compare ER and FR")
        public void loginUserPositiveTest(){
                Response response = LoginUser.LoginUser();
                response.then().assertThat().body("success", equalTo(true));
        }

        @Test
        @Description("Login unexisting user, wrong data")
        @Step("Compare ER and FR")
        public void loginUserWrongDataTest(){
                Response response = LoginUser.LoginUserWrongData();
                response.then().assertThat().statusCode(403).body("success", equalTo(false));
        }
}
