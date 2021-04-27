package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;

import static io.restassured.RestAssured.given;

public class UserUpdateTests {

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
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/user").
                then().log().all().statusCode(200);

        user.setFirstName("Jan");
        user.setLastName("Mieszko I");

        given().log().all().
                pathParam("username", user.getUsername()).
                body(user).
                contentType("application/json").
                when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}").
                then().log().all().statusCode(200);


        given().log().all().
                pathParam("username", user.getUsername()).
                when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/user/{username}").
                then().log().all().statusCode(200);
    }
}
