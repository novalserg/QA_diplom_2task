import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class Url {

    public RequestSpecification getUrlSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://stellarburgers.nomoreparties.site")
                .build();
    }
}