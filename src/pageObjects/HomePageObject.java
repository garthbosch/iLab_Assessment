package pageObjects;

import utils.SeleniumWebDriverUtils;

public class HomePageObject {

    private static final String CAREERS_LINK = "//nav[@class='navigation-right text-right']//li[normalize-space(.)='CAREERS']";
    private static final String PAGE_TITLE = "Home Page - iLAB";
    private SeleniumWebDriverUtils driver;

    public HomePageObject(SeleniumWebDriverUtils driver) {
        this.driver = driver;
    }

    public boolean verifyHomePage() throws Exception {
        return driver.getPageTitle().contains(PAGE_TITLE);
    }

    public void clickCareersLink() throws Exception {
        driver.clickByXpath(CAREERS_LINK);
    }
}