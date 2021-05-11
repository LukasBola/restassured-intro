package pl.javastart.restassured.test.http.methods.serialization;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;
import pl.javastart.restassured.test.TestBase;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class SerializationAndDeserializationTests extends TestBase {

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
        pet.setName("Stafik_777");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        Pet actualPet = given().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then().statusCode(200)
                .extract().as(Pet.class);

        assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        assertEquals(actualPet.getCategory().getId(), pet.getCategory().getId(), "Category id");
        assertEquals(actualPet.getCategory().getName(), pet.getCategory().getName(), "Category name");
        assertEquals(actualPet.getPhotoUrls().get(0), pet.getPhotoUrls().get(0), "Photo URL");
        assertEquals(actualPet.getTags().get(0).getId(), pet.getTags().get(0).getId(), "Pet tag id");
        assertEquals(actualPet.getTags().get(0).getName(), pet.getTags().get(0).getName(), "Pet tag name");
        assertEquals(actualPet.getStatus(), pet.getStatus(), "Pet status");
    }

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest2() {

        Category category = new Category();
        category.setId(1);
        category.setName("dogs");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("dogs-category");

        Pet pet = new Pet();
        pet.setId(56);
        pet.setCategory(category);
        pet.setName("Stafik_777");
        pet.setPhotoUrls(Collections.singletonList("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        //Metoda różni się od swojej poprzedniczki tylko rodzajem asercji
        given().body(pet).contentType("application/json")
                .when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet")
                .then()
                .assertThat().statusCode(200)
                .assertThat().contentType("application/json")
                .assertThat().body(
                "id", equalTo(56),
                "category.id", equalTo(1),
                "category.name", equalTo("dogs"),
                "name", equalTo("Stafik_777"),
                "photoUrls[0]", equalTo("http://staffik.pl/wp-content/uploads/2016/06/2014_BRUNO-staffik-z-Bullerbyn-768x621.jpg"),
                "tags[0].id", equalTo(1),
                "tags[0].name", equalTo("dogs-category"),
                "status", equalTo("available")
        );
    }

    @Test
    public void givenExistingPetIdWhenGetPetThenReturnPetTest() {
        SoftAssert softAssertion = new SoftAssert();

        Category category = new Category();
        category.setId(1);
        category.setName("cat");

        Tag tag = new Tag();
        tag.setId(1);
        tag.setName("cat-category");

        Pet pet = new Pet();
        pet.setId(600);
        pet.setCategory(category);
        pet.setName("Filemon");
        pet.setPhotoUrls(Collections.singletonList("https://wallpaperaccess.com/full/278984.jpg"));
        pet.setTags(Collections.singletonList(tag));
        pet.setStatus("available");

        Pet actualPet = given().
                body(pet).contentType("application/json").
                when().post("https://swaggerpetstore.przyklady.javastart.pl/v2/pet").
                then().statusCode(200).extract().as(Pet.class);

        Pet actualPetUsingGet = given().
                pathParam("petId", pet.getId()).
                when().get("https://swaggerpetstore.przyklady.javastart.pl/v2/pet/{petId}").
                then().statusCode(200).extract().as(Pet.class);


//        Tutaj celowo zmieniam dane aby wywołać błąd
//        tag.setId(12345);
//        tag.setName("cat-categoryy");

//        1 rozwiązanie - porównanie całych obiektów - nie pokazuje co dokładnie jest źle - dodano w ramach urozmaicenia i przypomnienie nadpisywania metody equals()
        softAssertion.assertTrue(pet.equals(actualPet));
        softAssertion.assertTrue(pet.equals(actualPetUsingGet));

//        2 rozwiązanie - porównanie pól obiektów
        softAssertion.assertEquals(actualPet.getId(), pet.getId(), "Pet id");
        softAssertion.assertEquals(actualPet.getCategory().getId(), pet.getCategory().getId(), "Category id");
        softAssertion.assertEquals(actualPet.getCategory().getName(), pet.getCategory().getName(), "Category name");
        softAssertion.assertEquals(actualPet.getPhotoUrls().get(0), pet.getPhotoUrls().get(0), "Photo URL");
        softAssertion.assertEquals(actualPet.getTags().get(0).getId(), pet.getTags().get(0).getId(), "Pet tag id");
        softAssertion.assertEquals(actualPet.getTags().get(0).getName(), pet.getTags().get(0).getName(), "Pet tag name");
        softAssertion.assertEquals(actualPet.getStatus(), pet.getStatus(), "Pet status");
        softAssertion.assertAll();
    }
}
