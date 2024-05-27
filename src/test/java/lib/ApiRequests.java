package lib;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequests {
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(BaseTestCase.urlPosts)
            .addFilter(new AllureRestAssured())
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .build();

    @Step("Make a Get-request with id")
    public Response getPostRequest(int id){
        return given()
                .spec(requestSpec)
                .get("/" + id)
                .andReturn();
    }

    @Step("Make a Delete-request with id")
    public Response deletePostRequest(int id){
        return given()
                .spec(requestSpec)
                .delete("/" + id)
                .andReturn();
    }

    @Step("Make a PUT-request")
    public Response putPostRequest(int id, Map<String, Object> bodyData){
        return given()
                .spec(requestSpec)
                .body(bodyData)
                .put("/" + id)
                .andReturn();
    }

    @Step("Make a POST-request")
    public Response createPostRequest(Map<String, Object> bodyData){
        return given()
                .spec(requestSpec)
                .body(bodyData)
                .post()
                .andReturn();
    }
}
