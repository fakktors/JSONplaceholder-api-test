package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import lib.ApiRequests;
import lib.Assertions;
import lib.BaseTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Getting a resource cases")
@Feature("Post api")
public class PostGetTest extends BaseTestCase {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully get post by id")
    @DisplayName("Positive get post")
    public void getPostByIdTest() {
        postId = 1;

        apiCoreRequests
                .makeGetRequest(urlPosts, postId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(postId));
    }

    @Test
    @Description("This test unsuccessfully get post, id not found")
    @DisplayName("Negative not found post")
    public void getPostNotFoundByIdTest() {
        Response responseFaultCode = apiCoreRequests
                .makeGetRequest(urlPosts, postId)
                .then()
                .assertThat()
                .statusCode(404)
                .extract().response();

        Assertions.assertJsonHasNotKey(responseFaultCode, "id");
    }
}
