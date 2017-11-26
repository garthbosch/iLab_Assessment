package pageObjects;

import gfb.logging.Logging;
import org.apache.log4j.Logger;
import utils.SeleniumWebDriverUtils;

public class CareerPageObject {

    private static final String SOUTH_AFRICA = "South Africa";
    private static final String SOUTH_AFRICA_LINK = "//div[@class='wpb_wrapper']//a[normalize-space(.)='" + SOUTH_AFRICA + "']";
    private static final String NORTH_AMERICA = "North America";
    private static final String NORTH_AMERICA_LINK = "//div[@class='wpb_wrapper']//a[normalize-space(.)='" + NORTH_AMERICA + "']";
    private static final String AUSTRALIA = "Australia";
    private static final String AUSTRALIA_LINK = "//div[@class='wpb_wrapper']//a[normalize-space(.)='" + AUSTRALIA + "']";
    private static final String UNITED_KINGDOM = "United Kingdom";
    private static final String UNITED_KINGDOM_LINK = "//div[@class='wpb_wrapper']//a[normalize-space(.)='" + UNITED_KINGDOM + "']";
    private static final String PAGE_TITLE = "CAREERS - iLAB";
    private static final String CAREER_LOCATION_TITLE = " - iLAB";
    private String POSITION_APPLYING_FOR_LINK = "//div[@class='wpjb-job-list wpjb-grid']//a[normalize-space(.)=";
    private SeleniumWebDriverUtils driver;
    public static Logger log = Logging.getLogger(true);

    public CareerPageObject(SeleniumWebDriverUtils driver) {
        this.driver = driver;
    }

    public boolean verifyCareerPage() throws Exception {
        return driver.getPageTitle().contains(PAGE_TITLE);
    }

    public boolean verifyCareerLocationPage(String locationOfPosition) throws Exception {
        return driver.getPageTitle().contains(locationOfPosition.toUpperCase() + CAREER_LOCATION_TITLE);
    }

    public void selectLocationOfPosition(String locationOfPosition) throws Exception {
        switch (locationOfPosition) {
            case SOUTH_AFRICA:
                driver.clickByXpath(SOUTH_AFRICA_LINK);
                break;

            case NORTH_AMERICA:
                driver.clickByXpath(NORTH_AMERICA_LINK);
                break;

            case AUSTRALIA:
                driver.clickByXpath(AUSTRALIA_LINK);
                break;

            case UNITED_KINGDOM:
                driver.clickByXpath(UNITED_KINGDOM_LINK);
                break;

            default:
                log.error("No Location was supplied, please supply a location");
        }
    }

    public void selectPositionToApplyFor(String positionApplyingFor) throws Exception {
        driver.clickByXpath(POSITION_APPLYING_FOR_LINK + "'" + positionApplyingFor + "']");
    }
}