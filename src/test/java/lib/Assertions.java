package lib;

import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;

public class Assertions {
    public static void assertJsonHasKey(Response Response, String expectedFieldName){
        Response.then().assertThat().body("$", hasKey(expectedFieldName));
    }

    public static void assertJsonHasFields(Response Response, String[] expectedFieldNames){
        for (String expectedFieldName : expectedFieldNames){
            Assertions.assertJsonHasKey(Response, expectedFieldName);
        }
    }

    public static void assertJsonHasNotKey(Response Response, String unexpectedFieldName){
        Response.then().assertThat().body("$", not(hasKey(unexpectedFieldName)));
    }

    public static void assertJsonHasNotValue(Response Response, String unexpectedValue){
        Response.then().assertThat().body("$", not(hasValue(unexpectedValue)));
    }
}
