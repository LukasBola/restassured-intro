package pl.javastart.restassured.test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class ResponseTimeTests {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedInSpecifiedTimeTest() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("cat-category");

        Tag tag1 = new Tag();
        tag1.setId(2);
        tag1.setName("kocur");
        List<Tag> tags = Arrays.asList(tag, tag1);

        Pet pet = new Pet();
        pet.setId(9867);
        pet.setCategory(category);
        pet.setName("Stafik_8w436890");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(tags);
        pet.setStatus("available");

        long responseTime = given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().log().all().statusCode(200)
                .extract().time();

        System.out.println("Response time is " + responseTime);

        assertTrue(responseTime <= 1800, "Response time");

    }

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedInSpecifiedTimeTestWithSpecBuilder() {

        ResponseSpecBuilder postResponseSpecBuilder = new ResponseSpecBuilder();
        postResponseSpecBuilder
                .expectResponseTime(Matchers.lessThan(1550L), TimeUnit.MILLISECONDS)
                .expectStatusCode(200);
        ResponseSpecification petCreationResponseSpecification = postResponseSpecBuilder.build();

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("cat-category");

        Tag tag1 = new Tag();
        tag1.setId(2);
        tag1.setName("kocur");
        List<Tag> tags = Arrays.asList(tag, tag1);

        Pet pet = new Pet();
        pet.setId(9867);
        pet.setCategory(category);
        pet.setName("Stafik_8w436890");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(tags);
        pet.setStatus("available");

        given().log().all().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().assertThat().spec(petCreationResponseSpecification);

    }

}
