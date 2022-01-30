**Selendroid Test App Automation**

Description
	That automation project is data driven based, Page Object Model(POM) project
	You can increase the number of scenarios by adding extra lines in excel file named as "testData.xlsx"
	There are 4 basic scripts; Exception Handling, Progress Bar Hangling, Sign Up and WebView interactions.
	"Tests" folder contains runnable test files
	"Pages" folder contains the functionality of scripts


Requirements
	Maven
	Appium Server
	Android Emulator


How to Run using Maven
	Run "mvn clean install" command at project root folder
	

Possible Errors and How to Solve
	Connectivity issues; 
		That script developed and tested on local emulator, thats why, you can get connection error.
	How To Solve
		Go to: BaseTests(Line 27,28) update Device Name and UDID attributes and run project
	
Expected Result

	There are 6 test scenarios, the expected result is 5 passed and 1 failed
	1 failiure is created on purpose, to try "Throw Exception function of application"