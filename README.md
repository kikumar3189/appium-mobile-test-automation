This project explores Appium capabilities for mobile test automation. It is developed using java client bindings, uses Junit as testing framework and maven as build and dependency management.

Pre-requisites to run:
1. Appium server should be started and listening at http://127.0.0.1:4723
2. Mobile emulator named as Pixel_2_Pie_API_28 should be running OR a physical Android device must be connected.

Steps to run :
1. Clone the repo locally.
2. Execute tests using following command in project root directory: mvn clean test 
3. By default tests are configured to run on emulator, however they can be configured to run on real device while getting driver from BaseDriver.getDriver("real"). A physical device must be connected with USB debugging enabled in this case.
