package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pageObjects.ApplyPageObject;
import pageObjects.CareerPageObject;
import pageObjects.HomePageObject;
import utils.GetDataFromDB;
import utils.SeleniumWebDriverUtils;
import utils.TestDataObject;

public class CareersApplicationTest extends CommonTests {

    protected HomePageObject homePage;
    protected CareerPageObject careerPage;
    protected ApplyPageObject applyPage;

    @Parameters({"url", "browserType", "waitTimeOut", "dataRetievalMethod", "locationOfPosition", "positionApplyingFor", "applicantName", "applicantEmailAddress", "message"})
    @BeforeClass
    public void setUp(String url, String browserType, Integer waitTimeOut, String dataRetievalMethod, String locationOfPosition, String positionApplyingFor, String applicantName,
                      String applicantEmailAddress, String message) throws Exception {
        testData = new TestDataObject();
        getDataFromDB = new GetDataFromDB();
        testData.setDataRetievalMethod(dataRetievalMethod);

        switch (testData.getDataRetievalMethod()) {
            case "Database":
                log.info("Test data will be retrieved from the Mongodb");
                testData = getDataFromDB.retrieveDataFromDB();
                break;

            case "TestScriptFile":
                log.info("Test data will be retrieved from the Test Script File");
                testData.setUrl(url);
                testData.setBrowserType(browserType);
                testData.setWaitTimeOut(waitTimeOut);
                testData.setLocationOfPosition(locationOfPosition);
                testData.setPositionApplyingFor(positionApplyingFor);
                testData.setApplicantName(applicantName);
                testData.setApplicantEmailAddress(applicantEmailAddress);
                testData.setMessage(message);
                break;

            default:
                log.error("Not a proper Data Retrieval Method was given, please provide one of the following: Database, TestScriptFile or DataTable");
        }
        driver = new SeleniumWebDriverUtils(testData.getUrl(), testData.getBrowserType(), testData.getWaitTimeOut());
        homePage = new HomePageObject(driver);
        careerPage = new CareerPageObject(driver);
        applyPage = new ApplyPageObject(driver);
        driver.startDriver();
    }

    @Test
    public void runCareerApplicationTest() throws Exception {
        Assert.assertTrue(homePage.verifyHomePage());
        homePage.clickCareersLink();
        Assert.assertTrue(careerPage.verifyCareerPage());
        careerPage.selectLocationOfPosition(testData.getLocationOfPosition());
        Assert.assertTrue(careerPage.verifyCareerLocationPage(testData.getLocationOfPosition()));
        careerPage.selectPositionToApplyFor(testData.getPositionApplyingFor());
        Assert.assertTrue(applyPage.verifyApplyPage(testData.getPositionApplyingFor()));
        applyPage.enterApplicantDetail(testData.getApplicantName(), testData.getApplicantEmailAddress(), testData.getMessage());
        Assert.assertTrue(applyPage.verifyApplicationMessage("There are errors in your form."));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        log.info("Testcase status is " + result.getStatus());
        log.info("Iresult status is " + result.FAILURE);

        switch (result.getStatus()) {
            case ITestResult.FAILURE:
                captureScreenshot.captureScreenshot(false, testData.getBrowserType(), getClass().getName(), driver);
        }
        driver.shutdown();
    }
}