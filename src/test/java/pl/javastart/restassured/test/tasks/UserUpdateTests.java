package pl.javastart.restassured.test.tasks;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.user.User;
import pl.javastart.restassured.test.TestBase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserUpdateTests extends TestBase {

    @Test
    public void givenCorrectUserDataWhenFirstNameLastNameAreUpdatedThenUserDataIsUpdatedTest() {

        //Specyfikacja żądania
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType("application/json");
        RequestSpecification defaultRequestSpecification = requestSpecBuilder.build();

        User user = new User();
        user.setId(57);
        user.setUsername("l2ukasss567");
        user.setFirstName("oldfirsname");
        user.setLastName("oldlastname");
        user.setEmail("lub@ass.pl");
        user.setPassword("password_strong");
        user.setPhone("+666 666 666");
        user.setUserStatus(666);

        //Specyfikacja odpowiedzi dla metody PUT i POST
        ResponseSpecBuilder postResponseSpecBuilder = new ResponseSpecBuilder();
        postResponseSpecBuilder
                .expectBody("code", equalTo(200))
                .expectBody("type", equalTo("unknown"))
                .expectBody("message", equalTo(Integer.toString(user.getId())))
                .expectStatusCode(200);
        ResponseSpecification userCreationResponseSpecification = postResponseSpecBuilder.build();


        given().spec(defaultRequestSpecification).
                body(user).
                contentType("application/json").
                when().post("user").
                then().
                assertThat().spec(userCreationResponseSpecification);

        given().spec(defaultRequestSpecification).
                pathParam("username", user.getUsername()).
                body(user).
                contentType("application/json").
                when().put("user/{username}").
                then().
                assertThat().spec(userCreationResponseSpecification);

        //Specyfikacja odpowiedzi dla żądania metodą GET
        ResponseSpecBuilder updateResponseSpecBuilder = new ResponseSpecBuilder();
        updateResponseSpecBuilder
                .expectBody("id", equalTo(user.getId()))
                .expectBody("username", equalTo(user.getUsername()))
                .expectBody("firstName", equalTo(user.getFirstName()))
                .expectBody("lastName", equalTo(user.getLastName()))
                .expectBody("email", equalTo(user.getEmail()))
                .expectBody("password", equalTo(user.getPassword()))
                .expectBody("phone", equalTo(user.getPhone()))
                .expectBody("userStatus", equalTo(user.getUserStatus()));
        ResponseSpecification getResponseSpecification = updateResponseSpecBuilder.build();

        given().spec(defaultRequestSpecification).
                pathParam("username", user.getUsername()).
                when().get("user/{username}").
                then().
                assertThat().spec(getResponseSpecification);
    }
}
