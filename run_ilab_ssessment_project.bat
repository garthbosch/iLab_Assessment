set projectPath=C:\Projects\iLab_Assessment
set classPath=%projectPath%\lib\*

java org.testng.TestNG %projectPath%\testcases\testScripts.xml
pause