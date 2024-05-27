package lib;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiRequests {
    @Step("Make a Get-request with id")
    public Response makeGetRequest(String url, int id){
        return given()
                .filter(new AllureRestAssured())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(url + "/" + id)
                .andReturn();
    }

    @Step("Make a Delete-request with id")
    public Response makeDeleteRequest(String url, int id){
        return given()
                .filter(new AllureRestAssured())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .delete(url + "/" + id)
                .andReturn();
    }

    @Step("Make a PUT-request")
    public Response makePutRequest(String url, int id, Map<String, Object> bodyData){
        return given()
                .filter(new AllureRestAssured())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(bodyData)
                .put(url + "/" + id)
                .andReturn();
    }

    @Step("Make a POST-request")
    public Response makePostRequest(String url, Map<String, Object> bodyData){
        return given()
                .filter(new AllureRestAssured())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(bodyData)
                .post(url)
                .andReturn();
    }
}
