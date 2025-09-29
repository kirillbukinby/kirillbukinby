package pages;

import elements.*;
import java.util.*;

import static config.ApiConstants.*;

public class ApiPage {

    private ApiElements apiElements;

    public ApiPage() {
        this.apiElements = new ApiElements(BASE_URL);
    }

    public ApiElements getApiElements() {
        return apiElements;
    }

    public Map<String, Object> createTestPostData() {
        Map<String, Object> postData = new HashMap<>();
        postData.put("title", TEST_TITLE);
        postData.put("body", TEST_BODY);
        postData.put("userId", TEST_USER_ID);
        return postData;
    }

    public Map<String, Object> createTestPostData(String title, String body, int userId) {
        Map<String, Object> postData = new HashMap<>();
        postData.put("title", title);
        postData.put("body", body);
        postData.put("userId", userId);
        return postData;
    }
}