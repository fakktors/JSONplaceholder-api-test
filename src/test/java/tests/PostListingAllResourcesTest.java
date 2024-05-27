package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostListingAllResourcesTest {

    private static Logger logger = LoggerFactory.getLogger(PostListingAllResourcesTest.class);

    @Test
    public void postsListingTopTenWords(){

        Response responseResources = RestAssured
                .get("https://jsonplaceholder.typicode.com/posts")
                .andReturn();

        List<Map<String, Object>> posts = responseResources.jsonPath().getList("");

        Map<String, Integer> wordCount = new HashMap<>();

        Pattern wordPattern = Pattern.compile("\\b\\w+\\b");

        for (Map<String, Object> post : posts) {
            String body = (String) post.get("body");
            Matcher matcher = wordPattern.matcher(body);
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sortedWords = wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .toList();

        for (int i = 0; i < sortedWords.size(); i++) {
            Map.Entry<String, Integer> entry = sortedWords.get(i);
            logger.info("{}. {} - {}", i + 1, entry.getKey(), entry.getValue());
        }
    }
}
