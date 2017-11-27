# iLAB Assessment

### Technologies Used
- Selenium WedDriver version 3.3.1
- TestNG version 6.8
- Java version 1.8
- Mongodb version 3.4.3
- Custom built logging utility using log4j
- IntelliJ IDEA version 2017.2.5
- Ant

### Data Providers
- XML File 
- Mongodb - 1 Collection and 1 Document

### Solution
Please note: My solution is for a windows environment. Check out all my code from https://github.com/garthbosch/iLab_Assessment.git

### Setting up mongodb
1. Install mongodb from https://www.mongodb.com/download-center#community
2. Create a folder C:\data\db.
3. Run mongodb as follows: <InstalledDirectory>\bin\mongod.exe.
4. Run data_setup/createMongoDB_And_InsertData.bat to setup data on the database.

### Requirement
1. Hybrid Approach - I've used a combination of Data Driven, Page Object Model Keyword Driven.
2. Descriptive programming - Reviewer to assess
3. Shared Repository - https://github.com/garthbosch/iLab_Assessment.git
4. Regular Expressions - I've used regular expressions in some xpaths - e.g of one: //nav[@class='navigation-right text-right']//li[normalize-space(.)='CAREERS']
5. Parametrisation - I'm using parameter from an XML file and a mongodb
6. Public Function - With java I'm using Public, Protected and Private methods.
7. Sorting and utilising of test data - I'm using an XML file and mongodb.
8. Reporting - I've built a custom logging utility that makes use of log4j. It will log all activities. Also if the test fail it will create a screenshot on the page where it is failing. TestNG also output some reports of the test.

### How to build the application
- Ant needs to be installed on your machine.
- Under the project folder run "ant all". This will build a ilab_assessment.jar which will also place this file under the lib folder.

### How to execute
- Via IntelliJ IDE - right-click and Run testcases/testScript.xml file
- Via .bat file - just run the run_ilab_ssessment_project.bat file.
- In the testcases/testScript you can select to get test data from the database or from the script file. For the dataRetievalMethod parameter either select "Database" or "TestScriptFile".