package pages;

import elements.*;

import static config.MyAccountPageConstants.*;
import static utils.BrowserHelper.*;

public class MyAccountPage {

    public MyAccountPage open() {
        openUrl(MY_ACCOUNT_URL);
        return this;
    }

    public MyAccountPageElements getAccountElements() {
        return new MyAccountPageElements();
    }
}