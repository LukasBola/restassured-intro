package pl.javastart.restassured.test.http.methods;

import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    /**
     GET
     **/
    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().log().all().
                pathParam("petId","56").
                when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}").
                then().log().all().statusCode(200);
    }

    /**
     POST
     **/
    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {
//        String pet = "{\n" +
//                "  \"id\": 56,\n" +
//                "  \"category\": {\n" +
//                "    \"id\": 1,\n" +
//                "    \"name\": \"dogs\"\n" +
//                "  },\n" +
//                "  \"name\": \"Stafik\",\n" +
//                "  \"photoUrls\": [\n" +
//                "    \"http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg\"\n" +
//                "  ],\n" +
//                "  \"tags\": [\n" +
//                "    {\n" +
//                "      \"id\": 1,\n" +
//                "      \"name\": \"dogs-category\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"status\": \"available\"\n" +
//                "}";

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(56);
        pet.setCategory(category);
        pet.setName("Stafik_666");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().
                body(pet).contentType("application/json").
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet").
                then().log().all().statusCode(200);
    }

    /**
     PUT
     **/
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

    /**
     DELETE
     **/
    @Test
    public void givenExistingPetIdWhenDeletingPetThenIsDeletedTest(){
//        String deletePet = "{\n" +
//                "  \"id\": 56,\n" +
//                "  \"category\": {\n" +
//                "    \"id\": 1,\n" +
//                "    \"name\": \"dogs\"\n" +
//                "  },\n" +
//                "  \"name\": \"Stafik_delete\",\n" +
//                "  \"photoUrls\": [\n" +
//                "    \"http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg\"\n" +
//                "  ],\n" +
//                "  \"tags\": [\n" +
//                "    {\n" +
//                "      \"id\": 1,\n" +
//                "      \"name\": \"dogs-category\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"status\": \"available\"\n" +
//                "}";
//
//        given().log().all().body(deletePet).contentType("application/json").
//                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet").
//                then().log().all().statusCode(200);

        given().log().all().
                pathParam("petId","56").
                when().delete("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}").
                then().log().all().statusCode(200);
    }
}
