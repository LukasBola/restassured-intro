package pl.javastart.restassured.test.jsonpath;

import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class JsonPathTest {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("cat");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("cat-tag");

        Pet pet = new Pet();
        pet.setId(600);
        pet.setCategory(category);
        pet.setName("Filemon");
        pet.setPhotoUrls(Collections.singletonList("https://wallpaperaccess.com/full/278984.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200);

        JsonPath jsonPathResponse = given().log().method().log().uri().pathParam("petId", pet.getId())
                .when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}")
                .then().log().all().statusCode(200).extract().jsonPath();

        String actualPetName = jsonPathResponse.getString("name");
        String actualCategoryName = jsonPathResponse.getString("category.name");
        String actualTagName = jsonPathResponse.getString("tags[0].name");

//        Tutaj celowo zmieniam dane aby wywołać błąd
//        tag.setName("dogi-tag");

        assertEquals(pet.getName(), actualPetName, "Pet name");
        assertEquals(pet.getCategory().getName(), actualCategoryName, "Category name");
        assertEquals(pet.getTags().get(0).getName(), actualTagName, "Tag name");
    }
}
