package pl.javastart.restassured.test.ssl;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BadSslTest {

    @BeforeClass
    public void beforeMethod() {
        // Metoda statyczna - ustawienie globalne
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    public void sslCertExpiredTest() {
        //Tutaj mamy błąd
        given().when().get("https://expired.badssl.com/").then().statusCode(200);
    }

    @Test
    public void sslCertExpiredRelaxedTest() {
        //Tutaj mamy metodę relaxedHTTPSValidation()
        given().relaxedHTTPSValidation().when().get("https://expired.badssl.com/").then().statusCode(200);

    }

    @Test
    public void sslCertExpiredSpecBuilderTest() {

        RequestSpecification requestSpecification = new RequestSpecBuilder().setRelaxedHTTPSValidation().build();
        //Tutaj mamy RequestSpecBuilder
        given().spec(requestSpecification).when().get("https://expired.badssl.com/").then().statusCode(200);

    }
}
