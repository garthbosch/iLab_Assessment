package pageObjects;

import gfb.logging.Logging;
import org.apache.log4j.Logger;
import utils.SeleniumWebDriverUtils;

import java.text.DecimalFormat;
import java.util.Random;

public class ApplyPageObject {

    private String pageTitle = " - iLAB";
    private SeleniumWebDriverUtils driver;
    private static Logger log = Logging.getLogger(true);
    private static final String APPLY_ONLINE_LINE = "//a[@data-wpjb-form='wpjb-form-job-apply']";
    private static final String APPLICANT_NAME_TEXTBOX = "//input[@id='applicant_name']";
    private static final String APPLICANT_EMAIL_TEXTBOX = "//input[@id='email']";
    private static final String APPLICANT_PHONE_TEXTBOX = "//input[@id='phone']";
    private static final String APPLICANT_MESSAGE_TEXTBOX = "//textarea[@id='message']";
    private static final String SEND_APPLICATION_BUTTON = "//input[@id='wpjb_submit']";
    private static final String MESSAGE_BANNER = "//span[@class='wpjb-glyphs wpjb-icon-attention']";

    public ApplyPageObject(SeleniumWebDriverUtils driver) {
        this.driver = driver;
    }

    public boolean verifyApplyPage(String positionApplyingFor) throws Exception {
        return driver.getPageTitle().contains(positionApplyingFor + pageTitle);
    }

    public void enterApplicantDetail(String applicantName, String applicantEmailAddress, String message) throws Exception {
        driver.clickByXpath(APPLY_ONLINE_LINE);
        driver.enterTextByXpath(APPLICANT_NAME_TEXTBOX, applicantName);
        driver.enterTextByXpath(APPLICANT_EMAIL_TEXTBOX, applicantEmailAddress);
        driver.enterTextByXpath(APPLICANT_PHONE_TEXTBOX, getPhoneNumber());
        driver.enterTextByXpath(APPLICANT_MESSAGE_TEXTBOX, message);
        driver.clickByXpath(SEND_APPLICATION_BUTTON);
    }

    private String getPhoneNumber() {
        Random rand = new Random();
        int num1 = 0 + (rand.nextInt(9) * 10) + rand.nextInt(9);
        int num2 = rand.nextInt(999);
        int num3 = rand.nextInt(10000);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

        String phoneNumber = df3.format(num1) + " " + df3.format(num2) + " " + df4.format(num3);

        log.info(phoneNumber);
        return phoneNumber;
    }

    public boolean verifyApplicationMessage(String expectedMessage) {
        return driver.getTextByXpath(MESSAGE_BANNER).contains(expectedMessage);
    }
}