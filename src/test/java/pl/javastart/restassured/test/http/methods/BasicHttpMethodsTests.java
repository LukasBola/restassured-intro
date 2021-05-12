package pl.javastart.restassured.test.http.methods;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests {

    @BeforeClass
    public void setupConfiguration() {
        RestAssured.baseURI = "https://swaggerpetstore.przyklady.javastart.pl";
        RestAssured.basePath = "v2";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        RestAssured.requestSpecification = new RequestSpecBuilder().setContentType("application/json").build();
        RestAssured.responseSpecification = new ResponseSpecBuilder().expectStatusCode(201).build();
    }

    /**
     * GET
     **/
    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().
                pathParam("petId", "777").
                when().get("pet/{petId}");
    }

    /**
     * POST
     **/
    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

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

        given().body(pet).
                when().post("pet");
    }

    /**
     * PUT
     **/
    @Test
    public void givenExistingPetWhenUpdatePetNameThenPetIsChangedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(56);
        pet.setCategory(category);
        pet.setName("Stafik_update_name_2");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");


        given().body(pet).
                when().put("pet");
    }

    /**
     * DELETE
     **/
    @Test
    public void givenExistingPetIdWhenDeletingPetThenIsDeletedTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");


        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(58);
        pet.setCategory(category);
        pet.setName("Stafik_delete");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        given().body(pet).
                when().post("pet");

        given().
                pathParam("petId", pet.getId())
                .when().
                delete("pet/{petId}");
    }
}
