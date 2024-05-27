package tests;
import io.qameta.allure.Description;
import lib.ApiRequests;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lib.DataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Create a resource cases")
@Feature("Post api")
public class PostCreateTest {

    private final ApiRequests apiCoreRequests = new ApiRequests();

    @Test
    @Description("This test successfully create new post")
    @DisplayName("Positive create post")
    public void createPostTest(){
        var postData = DataGenerator.generatePostDataBody();

        apiCoreRequests
                .createPostRequest(postData)
                .then()
                .assertThat()
                .statusCode(201);
                //.body("id", equalTo())
    }
}
