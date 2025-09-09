package tests;

import common.*;
import elements.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import static config.MainPageNavigationMenuConstants.*;
import static utils.AssertHelper.*;

public class MainPageNavigationMenuTests extends BaseDriver {

    private MainPageNavigationMenuElements navigationElements;

    @BeforeEach
    public void setUpTest() {
        navigationElements = mainPage.open().getNavigationElements();
    }

    @ParameterizedTest(name = "Проверка перехода в раздел {2}")
    @CsvSource({
            PIZZA_SECTION_KEY + ", " + PIZZA_URL_CONTAINS + ", '" + PIZZA_SECTION + "'",
            DESSERTS_SECTION_KEY + ", " + DESSERTS_URL_CONTAINS + ", '" + DESSERTS_SECTION + "'",
            DRINKS_SECTION_KEY + ", " + DRINKS_URL_CONTAINS + ", '" + DRINKS_SECTION + "'"
    })
    @DisplayName("Параметризированная проверка навигационного меню")
    public void shouldNavigateMenuParameterized(String sectionKey, String expectedUrlPart, String displayName) {
        // Act
        navigationElements.navigateToSection(sectionKey);

        // Assert
        assertUrlContains(expectedUrlPart, String.format(ERROR_SECTION_NAVIGATION, displayName));
    }
}