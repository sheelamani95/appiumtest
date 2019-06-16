Requirements:

1. Java 8
2. Maven 3 and above
3. Appium

To Run the tests and generate Report:

1. Start appium in terminal
2. build mobile application and update app full path in test/resources/application.properties
3. Open terminal and navigate to project folder where pom.xml is located
4. 
    - To run Functional Test type the command "mvn test verify  -Dcucumber.options="--tags @FunctionalTest" and Enter.
5. Now the tests will be executed and reports will be generated at project_folder/target/site/cucumber-reports/cucumber-html-reports
6. Open the overview-features.html file from the above mentioned path in a browser.
7. Report page contains multiple links please go through all.

