package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {
        String user = "{\n" +
                "  \"id\": 57,\n" +
                "  \"username\": \"lukasss\",\n" +
                "  \"firstName\": \"luk\",\n" +
                "  \"lastName\": \"Andf\",\n" +
                "  \"email\": \"luk@ass.com\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"phone\": \"+666 666 069\",\n" +
                "  \"userStatus\": 666\n" +
                "}";
        given().log().all().
                body(user).
                contentType("application/json").
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user").
                then().log().all().statusCode(200);

        given().log().all().
                pathParam("username", "lukasss").
                when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}").
                then().log().all().statusCode(200);
    }
}
