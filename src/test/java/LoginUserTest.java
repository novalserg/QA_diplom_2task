import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.hamcrest.CoreMatchers.equalTo;

public class LoginUserTest{

        @Test
        @Description("Login existing user, proper data")
        @Step("Compare ER and FR")
        public void loginUserPositiveTest(){
                Response response = CreateUser.CreateNewUser(CreateUser.dynamicUserData);
                LoginUser.loginUser();
                response.then().assertThat().body("success", equalTo(true));
        }

        @Test
        @Description("Login unexisting user, wrong data")
        @Step("Compare ER and FR")
        public void loginUserWrongDataTest(){
                Response response = LoginUser.loginUserWrongData();
                response.then().assertThat().statusCode(SC_FORBIDDEN).body("success", equalTo(false));

        }
}
