package Jackson;

import java.util.Collections;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class Main
{
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private final static class Article
    {
        private Long id;
        private String title;
        private List<String> tags;
    }

    static void WriteValue() throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Article article = new Article(1L, "Test Title", Collections.singletonList("Test Tag"));
        String json = mapper.writeValueAsString(article);
        System.out.println(json);
    }

    static void PrintPretty() throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        Article article = new Article(1L, "Test Title", Collections.singletonList("Test Tag"));
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(article);
        System.out.println(json);
    }

    static void ReadingFromString() throws JsonProcessingException
    {
        String json = "{\"id\":1,\"title\":\"Test Title\",\"tags\":[\"Test Tag\"]}";
        ObjectMapper mapper = new ObjectMapper();
        Article newArticle = mapper.readValue(json, Article.class);
        System.out.println(newArticle);
    }

    public static void main(String[] args) throws JsonProcessingException
    {
        // WriteValue();
        // PrintPretty();
        // ReadingFromString();
    }
}