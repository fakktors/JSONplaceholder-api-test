package lib;

import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

public class Assertions {
    public static void assertJsonHasKey(Response Response, String expectedFieldName){
        Response.then().assertThat().body("$", hasKey(expectedFieldName));
    }

    public static void assertJsonHasNotKey(Response Response, String unexpectedFieldName){
        Response.then().assertThat().body("$", not(hasKey(unexpectedFieldName)));
    }
}
