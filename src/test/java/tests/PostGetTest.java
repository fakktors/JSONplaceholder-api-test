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

        Response responsePost = apiCoreRequests
                .makeGetRequest(urlPosts, postId);

        assertEquals(200, responsePost.getStatusCode());
        assertEquals(postId, responsePost.getBody().jsonPath().getInt("id"));
    }

    @Test
    @Description("This test unsuccessfully get post, id not found")
    @DisplayName("Negative not found post")
    public void getPostNotFoundByIdTest() {
        Response responseFaultCode = apiCoreRequests
                .makeGetRequest(urlPosts, postId);

        assertEquals(404, responseFaultCode.getStatusCode());
        Assertions.assertJsonHasNotKey(responseFaultCode, "id");
    }
}
