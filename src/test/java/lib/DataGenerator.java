package lib;

import java.util.HashMap;
import java.util.Map;

public class DataGenerator {
    public static Map<String, Object> getPostDataBody(){
        Map<String, Object> body = new HashMap<>();
        body.put("id", 1);
        body.put("title", "Post Title");
        body.put("body", "Post Body");
        body.put("userId", 1);

        return body;
    }

}
