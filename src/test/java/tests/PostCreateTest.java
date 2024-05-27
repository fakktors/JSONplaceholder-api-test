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

@Epic("Create a resource cases")
@Feature("Post api")
public class PostCreateTest extends BaseTestCase {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully create new post")
    @DisplayName("Positive create post")
    public void createPostTest(){

        Response responseSuccessCreate = apiCoreRequests
                .makePostRequest(urlPosts, DataGenerator.generatePostDataBody())
                .then()
                .assertThat()
                .statusCode(201)
                .extract().response();

        Assertions.assertJsonHasKey(responseSuccessCreate, "id");

        /*
         * В документации описано что врнется больше полей, закомментированная проверка была бы уместна:
         * String[] expectedFields = {"id", "title", "body", "userId"};
         * Assertions.assertJsonHasFields(responseSuccessCreate, expectedFields);
         */
    }
}
