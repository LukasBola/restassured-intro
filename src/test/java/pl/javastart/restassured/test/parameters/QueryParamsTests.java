package pl.javastart.restassured.test.parameters;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        Tag tag1 = new Tag();
        tag1.setId(2);
        tag1.setName("kocur");

        List<Tag> tags = Arrays.asList(tag, tag1);

        Pet pet = new Pet();
        pet.setId(777);
        pet.setCategory(category);
        pet.setName("Filemon dziki kocur");
        pet.setPhotoUrls(Collections.singletonList("https://wallpaperaccess.com/full/278984.jpg"));
        pet.setTags(tags);
        pet.setStatus("sold");

        Pet petPost = given().log().all().
                body(pet).contentType("application/json").
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet").
                then().log().all().statusCode(200).extract().as(Pet.class);

        assertEquals(petPost.getStatus(), "sold", "Pet status");

//        1 sposób
        Pet[] actualPetList = given().log().all().queryParam("status", "sold")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                .then().log().all().statusCode(200).extract().as(Pet[].class);

//        int actualSoldPetListSize = actualPetList.length; //tutaj rozmiar bez zamiany na listę
        int actualSoldPetListSize = Arrays.asList(actualPetList).size();
        System.out.println("Sold pet number: " + actualSoldPetListSize);
        assertTrue(actualSoldPetListSize > 0, "List of pets");


//        2 sposób przy użyciu JsonPath
        List<Pet> actualPetList2 = given().log().all().queryParam("status", "sold")
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/findByStatus")
                .then().statusCode(200).extract().jsonPath().getList("", Pet.class);

//        Tutaj wyciągam imię drugiego zwierzaka z listy - just for fun
        System.out.println("Pet name is: " + actualPetList2.get(2).getName());

        assertTrue(actualPetList2.size() > 0, "List of pets");
    }
}
