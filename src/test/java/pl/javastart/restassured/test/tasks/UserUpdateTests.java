package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;
import pl.javastart.restassured.test.TestBase;

import static io.restassured.RestAssured.given;

public class UserUpdateTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedThenUserDataIsUpdatedTest() {

        User user = new User();
        user.setId(57);
        user.setUsername("lukasss");
        user.setFirstName("oldfirsname");
        user.setLastName("oldlastname");
        user.setEmail("lub@ass.pl");
        user.setPassword("password_strong");
        user.setPhone("+666 666 666");
        user.setUserStatus(666);


        given().log().all().
                body(user).
                contentType("application/json").
                when().post("user").
                then().log().all().statusCode(200);

        user.setFirstName("Jan");
        user.setLastName("Mieszko I");

        given().log().all().
                pathParam("username", user.getUsername()).
                body(user).
                contentType("application/json").
                when().put("user/{username}").
                then().log().all().statusCode(200);


        given().log().all().
                pathParam("username", user.getUsername()).
                when().get("user/{username}").
                then().log().all().statusCode(200);
    }
}
