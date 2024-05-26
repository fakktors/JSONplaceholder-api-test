package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import lib.ApiRequests;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



@Epic("Updating a resource cases")
@Feature("Post api")
public class PostUpdateTest extends BaseTestCase {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully update post by id")
    @DisplayName("Positive update post data and title")
    public void getPostByIdTest() {
        this.postId = 1;

        Response responsePost = apiCoreRequests
                .makeGetRequest(urlPosts, postId);

        String title = getIntFromJson(responsePost, "title");
        String body = getIntFromJson(responsePost, "body");

        Response responsePut = apiCoreRequests
                .makePutRequest(urlPosts, postId, DataGenerator.getPostDataBody());

        assertEquals(responsePut.getStatusCode(), 200);

        Response responseUpdatedPost = apiCoreRequests
                .makeGetRequest(urlPosts, postId);

        /*
        Assertions.assertJsonHasNotValue(responseUpdatedPost, title);
        Assertions.assertJsonHasNotValue(responseUpdatedPost, body);
         */
    }
}
