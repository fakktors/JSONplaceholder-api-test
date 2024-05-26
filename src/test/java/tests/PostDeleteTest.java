package tests;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lib.ApiRequests;
import io.restassured.response.Response;
import lib.BaseTestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Delete a resource cases")
@Feature("Post api")
public class PostDeleteTest extends BaseTestCase {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully delete post by id")
    @DisplayName("Positive delete")
    public void deletePostById(){
        this.postId = 1;

        Response responseDeleteResource = apiCoreRequests
                .makeDeleteRequest(urlPosts, postId);

        assertEquals(200, responseDeleteResource.getStatusCode());
        /*
         * Т.к это мок апи, дальнеший запрос не выолняется на проверку удаленного ресурса
         */
    }
}
