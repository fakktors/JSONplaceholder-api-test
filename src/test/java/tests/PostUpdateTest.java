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

@Epic("Updating a resource cases")
@Feature("Post api")
public class PostUpdateTest extends BaseTestCase {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully update post by id")
    @DisplayName("Positive update post data and title")
    public void updatePostByIdTest() {
        this.postId = 1;

        Response responsePost = apiCoreRequests
                .makeGetRequest(urlPosts, postId);

        String title = getIntFromJson(responsePost, "title");
        String body = getIntFromJson(responsePost, "body");

        apiCoreRequests
                .makePutRequest(urlPosts, postId, DataGenerator.generatePostDataBody())
                .then()
                .assertThat()
                .statusCode(200);

        Response responseUpdatedPost = apiCoreRequests
                .makeGetRequest(urlPosts, postId)
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        /*
        Assertions.assertJsonHasNotValue(responseUpdatedPost, title);
        Assertions.assertJsonHasNotValue(responseUpdatedPost, body);
         */
    }
}
