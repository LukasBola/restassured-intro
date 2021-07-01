package pl.javastart.restassured.test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase {

    @BeforeClass
    @Parameters({"baseUrl", "basePath"})
    public void setupConfiguration(@Optional("https://swaggerpetstore.przyklady.javastart.pl")
                                           String baseUrl,
                                   @Optional("v2")
                                           String basePath) {
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = basePath;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
