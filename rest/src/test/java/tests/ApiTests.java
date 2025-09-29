package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.util.*;

import static config.ApiConstants.*;
import static utils.AssertionHelper.*;

public class ApiTests extends BaseDriver {
    private ApiElements apiElements;

    @BeforeEach
    public void setUpTest() {
        apiElements = apiPage.getApiElements();
    }

    @Test
    @DisplayName("Проверка получения всех постов")
    public void shouldGetAllPosts() {
        // Act
        apiElements.getAllPosts();

        // Assert
        assertAll(
                () -> assertStatusCode(SUCCESS_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertResponseTimeLessThan(apiElements.getResponseTime(), MAX_RESPONSE_TIME, ERROR_RESPONSE_TIME),
                () -> assertResponseNotEmpty(apiElements.getResponseBody(), ERROR_RESPONSE_EMPTY),
                () -> assertListSize(EXPECTED_POSTS_COUNT, apiElements.getJsonArraySize(JSON_PATH_ROOT_ARRAY), ERROR_LIST_SIZE)
        );
    }

    @Test
    @DisplayName("Проверка получения конкретного поста")
    public void shouldGetSpecificPost() {
        // Act
        apiElements.getPostById(TEST_POST_ID);

        // Assert
        assertAll(
                () -> assertStatusCode(SUCCESS_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertResponseTimeLessThan(apiElements.getResponseTime(), MAX_RESPONSE_TIME, ERROR_RESPONSE_TIME),
                () -> assertJsonFieldExists(apiElements.getJsonField(FIELD_ID), ERROR_FIELD_MISSING),
                () -> assertJsonFieldExists(apiElements.getJsonField(FIELD_TITLE), ERROR_FIELD_MISSING),
                () -> assertJsonFieldExists(apiElements.getJsonField(FIELD_BODY), ERROR_FIELD_MISSING),
                () -> assertJsonFieldEquals((TEST_POST_ID), apiElements.getJsonField(FIELD_ID), ERROR_FIELD_VALUE)
        );
    }

    @ParameterizedTest(name = ERROR_PARAMETRIZED_POST_TEST_DISPLAY_NAME)
    @ValueSource(ints = {POST_ID_1, POST_ID_2, POST_ID_3, POST_ID_50, POST_ID_100})
    @DisplayName("Параметризированная проверка различных постов")
    public void shouldGetVariousPosts(int postId) {
        // Act
        apiElements.getPostById(postId);

        // Assert
        assertAll(
                () -> assertStatusCode(SUCCESS_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertJsonFieldEquals((postId), apiElements.getJsonField(FIELD_ID), ERROR_ASSERT_POST_ID_MATCH)
        );
    }

    @Test
    @DisplayName("Проверка создания нового поста")
    public void shouldCreateNewPost() {
        // Arrange
        Map<String, Object> postData = apiPage.createTestPostData();

        // Act
        apiElements.createPost(postData);

        // Assert
        assertAll(
                () -> assertStatusCode(CREATED_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertResponseTimeLessThan(apiElements.getResponseTime(), MAX_RESPONSE_TIME, ERROR_RESPONSE_TIME),
                () -> assertJsonFieldEquals(TEST_TITLE, apiElements.getJsonField(FIELD_TITLE),  ERROR_FIELD_VALUE),
                () -> assertJsonFieldEquals(TEST_BODY, apiElements.getJsonField(FIELD_BODY), ERROR_FIELD_VALUE),
                () -> assertJsonFieldEquals((TEST_USER_ID), apiElements.getJsonField(FIELD_USER_ID), ERROR_FIELD_VALUE)
        );
    }

    @Test
    @DisplayName("Проверка обновления поста")
    public void shouldUpdatePost() {
        // Arrange
        Map<String, Object> postData = apiPage.createTestPostData(UPDATED_TITLE, UPDATED_BODY, TEST_USER_ID);

        // Act
        apiElements.updatePost(TEST_POST_ID, postData);

        // Assert
        assertAll(
                () -> assertStatusCode(SUCCESS_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertResponseTimeLessThan(apiElements.getResponseTime(), MAX_RESPONSE_TIME, ERROR_RESPONSE_TIME),
                () -> assertJsonFieldEquals(UPDATED_TITLE, apiElements.getJsonField(FIELD_TITLE), ERROR_FIELD_VALUE),
                () -> assertJsonFieldEquals(UPDATED_BODY, apiElements.getJsonField(FIELD_BODY), ERROR_FIELD_VALUE)
        );
    }

    @Test
    @DisplayName("Проверка получения пользователя")
    public void shouldGetUser() {
        // Act
        apiElements.getUserById(TEST_USER_ID);

        // Assert
        assertAll(
                () -> assertStatusCode(SUCCESS_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertResponseTimeLessThan(apiElements.getResponseTime(), MAX_RESPONSE_TIME, ERROR_RESPONSE_TIME),
                () -> assertJsonFieldExists(apiElements.getJsonField(FIELD_NAME), ERROR_FIELD_MISSING),
                () -> assertJsonFieldExists(apiElements.getJsonField(FIELD_EMAIL), ERROR_FIELD_MISSING)
        );
    }

    @Test
    @DisplayName("Проверка удаления поста")
    public void shouldDeletePost() {
        // Act
        apiElements.deletePost(TEST_POST_ID);

        // Assert
        assertAll(
                () -> assertStatusCode(SUCCESS_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertResponseTimeLessThan(apiElements.getResponseTime(), MAX_RESPONSE_TIME, ERROR_RESPONSE_TIME)
        );
    }

    @ParameterizedTest(name = ERROR_PARAMETRIZED_TEST_DISPLAY_NAME)
    @CsvSource({
            USER_ID_1 + ", " + USER_NAME_1,
            USER_ID_2 + ", " + USER_NAME_2,
            USER_ID_3 + ", " + USER_NAME_3
    })
    @DisplayName("Параметризированная проверка пользователей")
    public void shouldGetVariousUsers(int userId, String expectedName) {
        // Act
        apiElements.getUserById(userId);

        // Assert
        assertAll(
                () -> assertStatusCode(SUCCESS_STATUS_CODE, apiElements.getStatusCode(), ERROR_STATUS_CODE),
                () -> assertJsonFieldEquals(expectedName, apiElements.getJsonField(FIELD_NAME), ERROR_ASSERT_USER_NAME_MATCH)
        );
    }
}