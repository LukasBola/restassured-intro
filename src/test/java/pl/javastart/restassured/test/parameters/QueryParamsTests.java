package pl.javastart.restassured.test.parameters;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Arrays;
import java.util.Collections;

import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class QueryParamsTests {

    @Test
    public void givenExistingPetWithStatusSoldWhenGetPetWithSoldStatusThenPetWithStatusIsReturnedTest() {
        SoftAssert softAssertion = new SoftAssert();

        Category category = new Category();
        category.setId(777);
        category.setName("cat");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("cat-category");

        Pet pet = new Pet();
        pet.setId(777);
        pet.setCategory(category);
        pet.setName("Filemon dziki kocur");
        pet.setPhotoUrls(Collections.singletonList("https://wallpaperaccess.com/full/278984.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("sold");

        Pet petPost = given().log().all().
                body(pet).contentType("application/json").
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet").
                then().log().all().statusCode(200).extract().as(Pet.class);

        assertEquals(petPost.getStatus(), "sold", "Pet status");

        Pet[] actualPetList = given().log().all().queryParam("status", "sold")
                .when().
                        get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                .then().
                        log().all().statusCode(200).extract().as(Pet[].class);
        ;

//        int actualSoldPetListSize = actualPetList.length;
        int actualSoldPetListSize = Arrays.asList(actualPetList).size();
        System.out.println("Sold pet number: " + actualSoldPetListSize);
        assertTrue(actualSoldPetListSize > 0, "List of pets");
    }
}
