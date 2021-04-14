package pl.javastart.restassured.test;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstRestAutomatedTest {

    @Test
    public void givenNonExistingPetIdWhenGetPetThenPetNotFoundTest(){
        given().log().uri().log().method().
                when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}",0).
                then().log().all().statusCode(404);
    }
}
