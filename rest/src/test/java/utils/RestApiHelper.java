package utils;

import io.restassured.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import java.util.*;

public class RestApiHelper {

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static Response sendGetRequest(String url) {
        System.out.println("🔍 Выполняем GET запрос: " + url);
        try {
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .get(url);
            System.out.println("✅ GET запрос выполнен. Статус: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении GET запроса: " + e.getMessage());
            throw new RuntimeException("Ошибка API запроса", e);
        }
    }

    public static Response sendPostRequest(String url, Map<String, Object> body) {
        System.out.println("🔍 Выполняем POST запрос: " + url);
        try {
            RequestSpecification request = RestAssured.given()
                    .header("Content-Type", "application/json");

            if (body != null && !body.isEmpty()) {
                request.body(body);
            }

            Response response = request.post(url);
            System.out.println("✅ POST запрос выполнен. Статус: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении POST запроса: " + e.getMessage());
            throw new RuntimeException("Ошибка API запроса", e);
        }
    }

    public static Response sendPutRequest(String url, Map<String, Object> body) {
        System.out.println("🔍 Выполняем PUT запрос: " + url);
        try {
            RequestSpecification request = RestAssured.given()
                    .header("Content-Type", "application/json");

            if (body != null && !body.isEmpty()) {
                request.body(body);
            }

            Response response = request.put(url);
            System.out.println("✅ PUT запрос выполнен. Статус: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении PUT запроса: " + e.getMessage());
            throw new RuntimeException("Ошибка API запроса", e);
        }
    }

    public static Response sendDeleteRequest(String url) {
        System.out.println("🔍 Выполняем DELETE запрос: " + url);
        try {
            Response response = RestAssured.given()
                    .header("Content-Type", "application/json")
                    .delete(url);
            System.out.println("✅ DELETE запрос выполнен. Статус: " + response.getStatusCode());
            return response;
        } catch (Exception e) {
            System.out.println("❌ Ошибка при выполнении DELETE запроса: " + e.getMessage());
            throw new RuntimeException("Ошибка API запроса", e);
        }
    }

    public static int getStatusCode(Response response) {
        return response.getStatusCode();
    }

    public static String getResponseBody(Response response) {
        return response.getBody().asString();
    }

    public static long getResponseTime(Response response) {
        return response.getTime();
    }

    public static String getJsonFieldValue(Response response, String fieldPath) {
        try {
            return response.jsonPath().getString(fieldPath);
        } catch (Exception e) {
            System.out.println("❌ Ошибка при получении поля '" + fieldPath + "': " + e.getMessage());
            return null;
        }
    }

    public static int getJsonArraySize(Response response, String arrayPath) {
        try {
            return response.jsonPath().getList(arrayPath).size();
        } catch (Exception e) {
            System.out.println("❌ Ошибка при получении размера массива: " + e.getMessage());
            return 0;
        }
    }
}