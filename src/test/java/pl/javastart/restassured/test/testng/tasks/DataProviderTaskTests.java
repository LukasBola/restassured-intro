package pl.javastart.restassured.test.testng.tasks;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;
import pl.javastart.restassured.test.testng.TestListener;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners(TestListener.class)
public class DataProviderTaskTests {

    @BeforeClass
    public void setupConfiguration() {
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
    }

    @DataProvider(name = "petStatuses")
    public Object[][] localDataProvider() {
        return new Object[][]{
                {57, "available"},
                {58, "pending"},
                {59, "sold"}
        };
    }

    @Test(dataProvider = "petStatuses")
    public void givenPetStatusWhenPetIsCreatedThenPetWithPetStatusIsAvailableTest(int petId, String petStatus) {

//        int petId = 57;

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(petId);
        pet.setCategory(category);
        pet.setName("Stafik_@#18bxs");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus(petStatus);

//        1 sposób asercji
        given()
                .body(pet)
                .when().post("pet")
                .then()
                .assertThat().body("status", equalTo(petStatus));

////        2 sposób asercji
//        String actualPetStatus = given()
//                .body(pet)
//                .when().post("pet")
//                .then()
//                .extract()
//                .jsonPath()
//                .getString("status");
//
//        assertEquals(actualPetStatus, petStatus, "Pet status is not as expected");

    }
}

