package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lib.ApiRequests;
import lib.BaseTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Delete a resource cases")
@Feature("Post api")
public class PostDeleteTest extends BaseTestCase {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully delete post by id")
    @DisplayName("Positive delete")
    public void deletePostById(){
        int postId = 1;

        apiCoreRequests
                .deletePostRequest(postId)
                .then()
                .assertThat()
                .statusCode(200);

        /*
         * This is a mock API, the distant request is not executed while checking the remote resource
         */
    }
}
