package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;
import pl.javastart.restassured.test.TestBase;

import static io.restassured.RestAssured.given;

public class UserCreationTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        User user = new User();
        user.setId(156);
        user.setUsername("lukasss");
        user.setFirstName("luk");
        user.setLastName("asssdr");
        user.setEmail("lub@ass.pl");
        user.setPassword("password_strong");
        user.setPhone("+666 666 666");
        user.setUserStatus(666);

        given().
                body(user).
                contentType("application/json").
                when().post("user").
                then().statusCode(200);

        given().
                pathParam("username", user.getUsername()).
                when().get("user/{username}").
                then().statusCode(200);
    }
}
