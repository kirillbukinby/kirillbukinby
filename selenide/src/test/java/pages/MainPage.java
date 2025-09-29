package pages;

import elements.*;

import static config.MainPageConstants.*;
import static utils.BrowserHelper.*;

public class MainPage {

    public MainPage open() {
        openUrl(MAIN_PAGE_URL);
        return this;
    }

    public MainPageElements getMainElements() {
        return new MainPageElements();
    }

    public MainPageNavigationMenuElements getNavigationElements() {
        return new MainPageNavigationMenuElements();
    }

    public MainPageSearchElements getSearchElements() {
        return new MainPageSearchElements();
    }
}