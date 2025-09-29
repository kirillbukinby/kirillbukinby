package pages;

import elements.*;

import static config.BonusProgramPageConstants.*;
import static utils.BrowserHelper.*;
import static utils.WaitHelper.*;

public class BonusProgramPage {

    public BonusProgramPage open() {
        openUrl(BONUS_PROGRAM_URL);
        waitForUrl(BONUS_PROGRAM_URL);
        return this;
    }

    public BonusProgramPageElements getBonusElements() {
        return new BonusProgramPageElements();
    }
}