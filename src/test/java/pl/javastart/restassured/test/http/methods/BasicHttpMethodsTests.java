package pl.javastart.restassured.test.http.methods;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;
import pl.javastart.restassured.test.TestBase;

import java.util.Collections;

import static io.restassured.RestAssured.given;

public class BasicHttpMethodsTests extends TestBase {

    /**
     * GET
     **/
    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        given().log().all().
                pathParam("petId", "777").
                when().get("pet/{petId}").
                then().log().all().statusCode(200);
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

        given().log().all().
                body(pet).contentType("application/json").
                when().post("pet").
                then().log().all().statusCode(200);
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


        given().log().all().
                body(pet).contentType("application/json").
                when().put("pet").
                then().log().all().statusCode(200);
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

        given().log().all().body(pet).contentType("application/json").
                when().post("pet").
                then().log().all().statusCode(200);

        given().
                log().all().
                pathParam("petId", pet.getId())
                .when().
                delete("pet/{petId}")
                .then().
                log().all().statusCode(200);
    }
}
