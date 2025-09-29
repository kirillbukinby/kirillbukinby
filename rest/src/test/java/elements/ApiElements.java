package elements;

import io.restassured.response.*;
import utils.*;
import java.util.*;

import static config.ApiConstants.*;

public class ApiElements {

    private Response lastResponse;
    private String baseUrl;

    public ApiElements(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // GET методы
    public ApiElements getAllPosts() {
        lastResponse = RestApiHelper.sendGetRequest(baseUrl + POSTS_ENDPOINT);
        return this;
    }

    public ApiElements getPostById(int postId) {
        lastResponse = RestApiHelper.sendGetRequest(baseUrl + POSTS_PATH + postId);
        return this;
    }

    public ApiElements getUserById(int userId) {
        lastResponse = RestApiHelper.sendGetRequest(baseUrl + USERS_PATH + userId);
        return this;
    }

    // POST методы
    public ApiElements createPost(Map<String, Object> postData) {
        lastResponse = RestApiHelper.sendPostRequest(baseUrl + POSTS_ENDPOINT, postData);
        return this;
    }

    // PUT методы
    public ApiElements updatePost(int postId, Map<String, Object> postData) {
        lastResponse = RestApiHelper.sendPutRequest(baseUrl + POSTS_PATH + postId, postData);
        return this;
    }

    // DELETE методы
    public ApiElements deletePost(int postId) {
        lastResponse = RestApiHelper.sendDeleteRequest(baseUrl + POSTS_PATH + postId);
        return this;
    }

    // Методы для получения данных из ответа
    public int getStatusCode() {
        return RestApiHelper.getStatusCode(lastResponse);
    }

    public String getResponseBody() {
        return RestApiHelper.getResponseBody(lastResponse);
    }

    public long getResponseTime() {
        return RestApiHelper.getResponseTime(lastResponse);
    }

    public String getJsonField(String fieldPath) {
        return RestApiHelper.getJsonFieldValue(lastResponse, fieldPath);
    }

    public int getJsonArraySize(String arrayPath) {
        return RestApiHelper.getJsonArraySize(lastResponse, arrayPath);
    }
}