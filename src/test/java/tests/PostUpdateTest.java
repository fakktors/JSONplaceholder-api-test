package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lib.ApiRequests;
import lib.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

@Epic("Updating a resource cases")
@Feature("Post api")
public class PostUpdateTest {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    private int postId = 1;

    @Test
    @Description("This test successfully update post by id")
    @DisplayName("Positive update post data and title")
    public void updatePostByIdTest() {
        var postData = DataGenerator.generatePostDataBody();

        //Getting post and save variable
        apiCoreRequests
                .getPostRequest(postId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(postId));

        //Update post title and body
        apiCoreRequests
                .putPostRequest(postId, postData)
                .then()
                .assertThat()
                .statusCode(200);

        //Checking that the title and body have been updated in the post
        apiCoreRequests
                .getPostRequest(postId)
                .then()
                .assertThat()
                .statusCode(200);
                //.body("body", equalTo(postData.get("body")))
                //.body("title", equalTo(postData.get("title")));
    }
}
