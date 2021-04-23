package pl.javastart.restassured.test.http.methods;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {
        String pet = "{\n" +
                "  \"id\": 56,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"dogs\"\n" +
                "  },\n" +
                "  \"name\": \"Stafik\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"dogs-category\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given().log().all().
                body(pet).contentType("application/json").
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet").
                then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().log().all().
                pathParam("petId","56").
                when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}").
                then().log().all().statusCode(200);
    }

    @Test
    public void givenExistingPetWhenUpdatePetNameThenPetIsChangedTest(){
        String updatePet = "{\n" +
                "  \"id\": 56,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"dogs\"\n" +
                "  },\n" +
                "  \"name\": \"Stafik_update_name\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"dogs-category\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        given().log().all().
                body(updatePet).contentType("application/json").
                when().put("https://swaggerpetstore.przyklady.javastart.pl/v2/pet").
                then().log().all().statusCode(200);
    }
}
