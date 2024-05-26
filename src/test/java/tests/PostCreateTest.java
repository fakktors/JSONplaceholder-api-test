package tests;
import io.qameta.allure.Description;
import lib.ApiRequests;
import lib.Assertions;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import lib.BaseTestCase;
import lib.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Create a resource cases")
@Feature("Post api")
public class PostCreateTest extends BaseTestCase {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully create new post")
    @DisplayName("Positive create post")
    public void createPostTest(){

        Response responseSuccessCreate = apiCoreRequests
                .makePostRequest(urlPosts, DataGenerator.getPostDataBody());

        assertEquals(201, responseSuccessCreate.statusCode());
        Assertions.assertJsonHasKey(responseSuccessCreate, "id");

        /*
         * В документации описано что врнется больше полей, закомментированная проверка была бы уместна:
         * String[] expectedFields = {"id", "title", "body", "userId"};
         * Assertions.assertJsonHasFields(responseSuccessCreate, expectedFields);
         */
    }
}
