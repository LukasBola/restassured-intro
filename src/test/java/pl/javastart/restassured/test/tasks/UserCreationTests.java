package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;

import static io.restassured.RestAssured.given;

public class UserCreationTests {

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

        given().log().all().
                body(user).
                contentType("application/json").
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user").
                then().log().all().statusCode(200);

        given().log().all().
                pathParam("username", user.getUsername()).
                when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}").
                then().log().all().statusCode(200);
    }
}
