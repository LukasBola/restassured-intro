package pl.javastart.restassured.test.ssl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class InvalidCertTest {

    @Test
    public void untrustedRootTest() {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setRelaxedHTTPSValidation().build();
        given().spec(requestSpecification).when().get("https://untrusted-root.badssl.com/").then().statusCode(200);
    }
}
