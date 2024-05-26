package lib;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.hasKey;


public class BaseTestCase {
    protected final String urlPosts = "https://jsonplaceholder.typicode.com/posts";
    protected Integer postId = 0;

    protected String getIntFromJson(Response Response, String name){
        Response.then().assertThat().body("$",hasKey(name));
        return Response.jsonPath().getString(name);
    }
}
