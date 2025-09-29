package common;

import org.junit.jupiter.api.*;
import pages.*;

import java.sql.SQLException;

public class BaseDriver {
    protected DatabasePage databasePage;

    @BeforeEach
    void setUp() throws SQLException {
        initPages();
    }

    private void initPages() throws SQLException {
        databasePage = new DatabasePage();
    }

    @AfterEach
    public void tearDown() {
        if (databasePage != null) {
            databasePage.close();
        }
    }
}