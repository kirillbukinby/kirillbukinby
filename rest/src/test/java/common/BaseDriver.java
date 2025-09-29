package common;

import org.junit.jupiter.api.*;
import pages.*;

public class BaseDriver {
    protected ApiPage apiPage;

    @BeforeEach
    void setUp() {
        initPages();
    }

    private void initPages() {
        apiPage = new ApiPage();
    }
}