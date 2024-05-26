package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lib.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Getting a resource cases")
@Feature("Post api")
public class PostGetTest {
    final String url = "https://jsonplaceholder.typicode.com/posts/{id}";
    Integer postId = 0;

    @Test
    @Description("This test successfully get post by id")
    @DisplayName("Positive get post")
    public void getPostByIdTest() {
        this.postId = 1;

        Response responsePost = RestAssured
                .given()
                .pathParam("id", postId)
                .when()
                .get(url)
                .andReturn();

        assertEquals(200, responsePost.getStatusCode());
        assertEquals(postId, responsePost.getBody().jsonPath().getInt("id"));
    }

    @Test
    @Description("This test unsuccessfully get post, id not found")
    @DisplayName("Negative get post")
    public void getPostNotFoundByIdTest() {
        Response responsePost = RestAssured
                .given()
                .pathParam("id", postId)
                .when()
                .get(url)
                .andReturn();

        assertEquals(404, responsePost.getStatusCode());
        Assertions.assertJsonHasNotKey(responsePost, "id");
    }
}
