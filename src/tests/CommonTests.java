package tests;

import gfb.logging.Logging;
import org.apache.log4j.Logger;
import utils.CaptureScreenShot;
import utils.GetDataFromDB;
import utils.SeleniumWebDriverUtils;
import utils.TestDataObject;

public class CommonTests {

    protected SeleniumWebDriverUtils driver;
    protected TestDataObject testData;
    protected CaptureScreenShot captureScreenshot = new CaptureScreenShot();
    protected GetDataFromDB getDataFromDB;
    public static Logger log = Logging.getLogger(true);
}
