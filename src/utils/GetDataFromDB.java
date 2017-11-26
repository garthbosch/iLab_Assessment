package utils;

import com.mongodb.*;
import gfb.logging.Logging;
import org.apache.log4j.Logger;

/**
 * @author Garth.Bosch
 */
public class GetDataFromDB {

    private static TestDataObject testData = new TestDataObject();
    private static Logger log = Logging.getLogger(true);

    public TestDataObject retrieveDataFromDB() {
        try {
            /*=========Connect to MongoDB=========*/
            MongoClient mongo = new MongoClient("127.0.0.1", 27017);

            /*=========Get database=========*/
            DB db = mongo.getDB("ilab_assessment");
            log.info("Show databases:" + db.getMongo());
            /*=========Get Collection=========*/
            DBCollection dbCollection = db.getCollection("testdata");
            /*=========Loop through DB Objects and find data=========*/
            try (DBCursor cursor = dbCollection.find()) {
                while (cursor.hasNext()) {
                    BasicDBObject dbObject = (BasicDBObject) cursor.next();
                    testData.setUrl(dbObject.getString("url"));
                    testData.setBrowserType(dbObject.getString("browserType"));
                    testData.setWaitTimeOut(dbObject.getString("waitTimeOut").hashCode());
                    testData.setLocationOfPosition(dbObject.getString("locationOfPosition"));
                    testData.setPositionApplyingFor(dbObject.getString("positionApplyingFor"));
                    testData.setApplicantName(dbObject.getString("applicantName"));
                    testData.setApplicantEmailAddress(dbObject.getString("applicantEmailAddress"));
                    testData.setMessage(dbObject.getString("message"));
                }
            }
            log.info("Data successfully retrieved from the Database!!!");
            mongo.close();
        } catch (Exception ex) {
            log.error("Unable to do getResultSetFromDB - " + ex.getMessage());
        }
        return testData;
    }
}