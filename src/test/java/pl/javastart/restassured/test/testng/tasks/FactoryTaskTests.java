package pl.javastart.restassured.test.testng.tasks;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
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
public class FactoryTaskTests {

    private int petId;
    private String petStatus;
    private String petName;

    public FactoryTaskTests(int petId, String petStatus, String petName) {
        this.petId = petId;
        this.petStatus = petStatus;
        this.petName = petName;
    }

    @Factory
    public static Object[] factoryMethod() {
        FactoryTaskTests firstTestToExecute = new FactoryTaskTests(66, "available", "Stafik Reksio");
        FactoryTaskTests secondTestToExecute = new FactoryTaskTests(67, "pending", "Stafik Burek");
        FactoryTaskTests thirdTestToExecute = new FactoryTaskTests(68, "sold", "Stafik Pluszowy");
        return new Object[]{
                firstTestToExecute,
                secondTestToExecute,
                thirdTestToExecute
        };
    }

    @BeforeClass
    public void setupConfiguration() {
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).build();
    }


    @Test
    public void givenPetStatusAndNameWhenPetIsCreatedThenPetWithPetStatusAndNameIsAvailableTest() {


        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(petId);
        pet.setCategory(category);
        pet.setName(petName);
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus(petStatus);

////        1 sposób - hard assertion
//        given()
//                .body(pet)
//                .when().post("pet")
//                .then()
//                .assertThat().body("status", equalTo("fail"))
//                .assertThat().body("name", equalTo("fail"))
//                .assertThat().body("id", equalTo("fail"));


//        2 sposób - soft assertion
        given()
                .body(pet)
                .when().post("pet")
                .then()
                .assertThat()
                .body(
                        "id", equalTo(petId),
                        "name", equalTo(petName),
                        "status", equalTo(petStatus));
    }
}

