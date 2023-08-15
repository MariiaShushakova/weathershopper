# Weather shopper UI tests
QA technical tasks 
## Stack
- Java 17.0.2
- Maven 3.9.0
- Test Framework: Selenium, TestNG, JDI + Selenoid

## Docker set up
1. Download docker
2. Pull 3 images:

```docker pull aerokube/selenoid:latest-release```

```docker pull aerokube/selenoid-ui ```

```docker pull selenoid/vnc:chrome_108.0```
4. Go to the project dir "path/weathershopper>" and run 2 containers (*Windows format*):
    1. ```C:\ **PATH** \weathershopper> docker run -d --name selenoid -p 4444:4444 -v //var/run/docker.sock:/var/run/docker.sock -v /C/ **PATH** /weathershopper/etc/selenoid/:/etc/selenoid/:ro aerokube/selenoid:latest-release ```
    2. ``` C:\ **PATH** \weathershopper> docker run -d --name selenoid-ui --link selenoid -p 8080:8080 aerokube/selenoid-ui --selenoid-uri=http://selenoid:4444 ```

If everything goes well, you can observe tests here: http://localhost:8080/#/

## Run tests
###Run tests in Docker:

Run command from 'weathershopper' folder:

```mvn clean test site -Ddomain=https://weathershopper.pythonanywhere.com ```

###Run tests via WebDriver (locally):

1. Go to TestInit.java class > initDriver(): uncomment block under "local" and comment block "docker";
2. Go to pom.xml file > <properties>: comment block under "docker" (4 lines);
3. ```mvn clean test```

