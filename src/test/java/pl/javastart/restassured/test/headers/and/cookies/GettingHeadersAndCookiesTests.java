package pl.javastart.restassured.test.headers.and.cookies;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pl.javastart.main.pojo.Category;
import pl.javastart.main.pojo.Pet;
import pl.javastart.main.pojo.Tag;
import pl.javastart.restassured.test.TestBase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class GettingHeadersAndCookiesTests extends TestBase {

    @Test
    public void givenPetWhenPostPetThenPetIsCreatedTest() {

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

        Response response = given().log().all().body(pet).contentType("application/json")
                .when().post("pet")
                .then().log().all().extract().response();

        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        Headers responseHeaders = response.getHeaders();
        Map<String, String> cookies = response.getCookies();

        assertEquals(statusLine, "HTTP/1.1 200 OK", "Status line");
        assertEquals(statusCode, 200, "Status code");
        assertNotNull(responseHeaders.get("Date"));
        assertEquals(responseHeaders.get("Server").getValue(), "nginx/1.10.3", "Server header");
        assertEquals(responseHeaders.get("Content-Type").getValue(), "application/json", "Content-Type header");
        assertTrue(cookies.isEmpty(), "Cookies are empty");
    }
}
