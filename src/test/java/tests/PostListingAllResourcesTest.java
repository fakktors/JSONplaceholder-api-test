package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostListingAllResourcesTest {

    @Test
    public void postsListingTopTenWords(){

        Response responseResources = RestAssured
                .get("https://jsonplaceholder.typicode.com/posts")
                .andReturn();

        List<Map<String, Object>> posts = responseResources.jsonPath().getList("");

        // Храним слова в формате ключ - значение для вывода в формате задачи
        Map<String, Integer> wordCount = new HashMap<>();

        // Подготовка регулярки для извлечения слов
        Pattern wordPattern = Pattern.compile("\\b\\w+\\b");

        // Извлекаем слова из текста, смотрим только body, далее приводим их к нижнему
        // регистру чтобы избежать дублирования.
        for (Map<String, Object> post : posts) {
            String body = (String) post.get("body");
            Matcher matcher = wordPattern.matcher(body);
            while (matcher.find()) {
                String word = matcher.group().toLowerCase();
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }

        // Сортируем слова по частоте вхождения
        List<Map.Entry<String, Integer>> sortedWords = wordCount.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .toList();

        // Выводим топ-10 слов в формате ключ-значение/ слово - кол-во вхождений
        for (int i = 0; i < sortedWords.size(); i++) {
            Map.Entry<String, Integer> entry = sortedWords.get(i);
            System.out.printf("%d. %s - %d%n", i + 1, entry.getKey(), entry.getValue());
        }
    }
}
