package pl.javastart.restassured.test.tasks;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;
import pl.javastart.restassured.test.TestBase;
import pl.javastart.restassured.test.testng.TestListener;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners(TestListener.class)
public class UserCreationTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenCreateUserThenUserIsCreatedTest() {

        User user = new User();
        user.setId(156);
        user.setUsername("lukasss2345");
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
                then().
                assertThat().statusCode(200).
                assertThat().body("code", equalTo(200)).
                assertThat().body("type", equalTo("unknown")).
                assertThat().body("message", equalTo(Integer.toString(user.getId())));

        given().
                pathParam("username", user.getUsername()).
                when().get("user/{username}").
                then().
                assertThat().statusCode(200).
                assertThat().body("id", equalTo(user.getId())).
                assertThat().body("username", equalTo(user.getUsername())).
                assertThat().body("firstName", equalTo(user.getFirstName())).
                assertThat().body("lastName", equalTo(user.getLastName())).
                assertThat().body("email", equalTo(user.getEmail())).
                assertThat().body("password", equalTo(user.getPassword())).
                assertThat().body("phone", equalTo(user.getPhone())).
                assertThat().body("userStatus", equalTo(user.getUserStatus()));
    }
}
