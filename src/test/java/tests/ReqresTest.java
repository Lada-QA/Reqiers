package tests;

import org.testng.annotations.Test;
import reqres_objects.User;

import static io.restassured.RestAssured.given;

public class ReqresTest {

    @Test
    public  void postCreateUserTest() {
        //https://reqres.in/api/users
        User user = User.builder()
                .name("morpheus")
                .job("leader")
                .build();
        given()
                .body(user)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201);
    }
}
