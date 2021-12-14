package tests;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import reqres_objects.DataList;
import reqres_objects.ResourceUserList;

import static io.restassured.RestAssured.given;

public class ReqresGetTest {
    @Test
    public void getSingleResourceNotFound() {
        String body = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown/23").
                then()
                .statusCode(404)
                .extract().body().asString();
        Assert.assertEquals(body, "{}");
    }

    @Test
    public void getListResourceTest() {
        String body = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        ResourceUserList resourceUserList = new Gson().fromJson(body, ResourceUserList.class);
        String nameUserFromAPI = resourceUserList.getData().get(0).getName();
        String pantoneValueFromAPI = resourceUserList.getData().get(4).getPantoneValue();
        Assert.assertEquals(nameUserFromAPI, "cerulean");
        Assert.assertEquals(pantoneValueFromAPI, "17-1456");
    }

    @Test
    public void getListUsersTest() {
        String body = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        DataList dataList = new Gson().fromJson(body, DataList.class);
        String userLastNameFromAPI = dataList.getData().get(0).getLastName();
        String avatarURLFRomAPI = dataList.getData().get(5).getAvatar();
        Assert.assertEquals(userLastNameFromAPI, "Lawson");
        Assert.assertEquals(avatarURLFRomAPI, "https://reqres.in/img/faces/12-image.jpg");
    }

    @Test
    public void getDelayedResponseTest() {
        String body = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        DataList dataList = new Gson().fromJson(body, DataList.class);
        String nameFromAPI = dataList.getData().get(0).getFirstName();
        String emailFromAPI = dataList.getData().get(0).getEmail();
        Assert.assertEquals(nameFromAPI, "George");
        Assert.assertEquals(emailFromAPI, "george.bluth@reqres.in");
    }

    @Test
    public void getSingleResource() {
        String body = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/unknown/2").
                then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        Assert.assertEquals(body, "{\"data\":{\"id\":2,\"name\":\"fuchsia rose\",\"year\":2001,\"color\":\"#C74375\",\"pantone_value\":\"17-2031\"},\"support\":{\"url\":\"https://reqres.in/#support-heading\",\"text\":" +
                "\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}");
    }

    @Test
    public void getSingleUserTest() {
        String body = given()
                .log().all()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().asString();
        Assert.assertEquals(body, "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\",\"first_name\":\"Janet\",\"last_name\"" +
                ":\"Weaver\",\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"},\"support\":{\"url\":\"https://reqres.in/#support-heading\"," +
                "\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}");
    }

    @Test
    public void getSingleUserNotFound() {
        String body = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get("https://reqres.in/api/users/23").
                then()
                .statusCode(404)
                .extract().body().asString();
        Assert.assertEquals(body, "{}");
    }
}
