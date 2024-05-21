Title and Description : Tendable. Automating the to menu items on the Tendable site, Marketing Contact Us form and validating the error messages.

Languages Used: Java and Selenium

Tools Used : Maven

Dependencies required: TestNG and Github

Strategy Used:
1. Installed maven and added the above dependencies to run the project. 
2. Test file – File containing all the test that are required to run with BeforeTest where the browser is launched befire every test runs and AfterTest where the driver is quit after every test
3. ObjectRepository.properties file – Where all the objects required for the project have been placed
4. Parser file – Used to file the objects placed in the object repository file using “#” as the split character either by name/xpath/linktext

Execution:
This project can be imported in VS Studio Code or Eclipse and can directly run the test by right clicking on it and selecting Run Test/Tests
