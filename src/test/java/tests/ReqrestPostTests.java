package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import reqres_objects.RegisterUser;
import reqres_objects.UnregisterUser;
import reqres_objects.UserLoginSuccessful;

import static io.restassured.RestAssured.given;

public class ReqrestPostTests {

    @Test
    public void postRegisterSuccessfulUserTest() {
        RegisterUser registerUser = RegisterUser.builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();
        String body = given()
                .contentType("application/json; charset=UTF-8")
                .body(registerUser).
                when()
                .post("https://reqres.in/api/register").
                then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        Assert.assertEquals(body, "{\"id\":4,\"token\":\"QpwL5tke4Pnpja7X4\"}");
    }

    @Test
    public void postRegisterUnsuccessfulTest() {
        UnregisterUser unregisterUser = UnregisterUser.builder()
                .email("sydney@fife")
                .build();
        String body = given()
                .body(unregisterUser)
                .contentType("application/json; charset=UTF-8").
                when()
                .post("https://reqres.in/api/register").
                then()
                .log().all()
                .statusCode(400)
                .extract().body().asString();
        Assert.assertEquals(body, "{\"error\":\"Missing password\"}");
    }

    @Test
    public void postLoginUnsuccessful() {
        UnregisterUser unregisterUser = UnregisterUser.builder()
                .email("peter@klaven")
                .build();
        String body = given()
                .body(unregisterUser)
                .contentType("application/json; charset=UTF-8").
                when()
                .post("https://reqres.in/api/register").
                then()
                .log().all()
                .statusCode(400)
                .extract().body().asString();
        Assert.assertEquals(body, "{\"error\":\"Missing password\"}");
    }

    @Test
    public void postLoginSuccessfulTest() {
        UserLoginSuccessful userLoginSuccessful = UserLoginSuccessful.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();
        String body = given()
                .body(userLoginSuccessful)
                .contentType("application/json; charset=UTF-8").
                when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        Assert.assertEquals(body, "{\"token\":\"QpwL5tke4Pnpja7X4\"}");
    }
}
