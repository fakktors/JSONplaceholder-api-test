package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lib.ApiRequests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@Epic("Getting a resource cases")
@Feature("Post api")
public class PostGetTest {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    private int postId = 0;

    @Test
    @Description("This test successfully get post by id")
    @DisplayName("Positive get post")
    public void getPostByIdTest() {
        this.postId = 1;

        apiCoreRequests
                .getPostRequest(postId)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(postId));
    }

    @Test
    @Description("This test unsuccessfully get post, id not found")
    @DisplayName("Negative not found post")
    public void getPostNotFoundByIdTest() {
        apiCoreRequests
                .getPostRequest(postId)
                .then()
                .assertThat()
                .statusCode(404)
                .body("id", not(hasKey("id")));
    }
}
