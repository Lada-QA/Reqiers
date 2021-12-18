package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import reqres_objects.*;

import static io.restassured.RestAssured.given;

public class ReqresPutPatchDeleteTest {

    @Test
    public void postCreateUserTest() {
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

    @Test
    public void deleteUserTest() {
        given()
                .log().all()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void putUserTest() {
        User user = User.builder()
                .name("Neo")
                .job("zion resident")
                .build();
        given()
                .body(user)
                .when()
                .contentType("application/json; charset=UTF-8")
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().body().asString();
        String nameUserFromAPI = user.getName();
        String jobUserFromApi = user.getJob();
        Assert.assertEquals(nameUserFromAPI, user.getName());
        Assert.assertEquals(jobUserFromApi, user.getJob());
    }

    @Test
    public void patchUpdateUserTest() {
        User user = User.builder()
                .name("Ginger")
                .job("QA")
                .build();
        given()
                .body(user)
                .contentType("application/json; charset=UTF-8")
                .log().all()
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .extract().body().asString();
        String nameUserFromAPI = user.getName();
        String jobUserFromAPI = user.getJob();
        Assert.assertEquals(nameUserFromAPI,  user.getName());
        Assert.assertEquals(jobUserFromAPI, user.getJob());
    }
}
