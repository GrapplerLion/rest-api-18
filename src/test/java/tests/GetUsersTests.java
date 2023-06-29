package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUsersTests {

    @Test
    void getListUsers(){
        Integer expectedTotal = 12;
        Integer actualTotal =  given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .extract().path("total");

        assertEquals(expectedTotal, actualTotal);
    }
    @Test
    void getSingleUsers(){
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();
    }
    @Test
    void getSingleUsersNotFound(){
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404)
                .log().status()
                .log().body();
    }
    @Test
    void getListResourses(){
        given()
                .log().uri()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();
    }

}
