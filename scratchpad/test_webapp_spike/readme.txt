This is a very simple exampe to demonstrate how to test a web-application with a form using htmlunit and JWebUnit. 

(1) Install Maven 2
(2) Download and Unpack Apache Tomcat e.g. Version 6
(3) Start Tomcat by executing the startup.sh file in the bin directory (Mac, Unix) or the startup.bat (Windows)
(4) Open Browser and check if Tomcat is running (http://localhost:8080)

In the default installation of Tomcat there is a simple JSF Webapplication "Number Guesser":

http://localhost:8080/examples/jsp/num/numguess.jsp

Check it out. The Unit test in this project tests this number guesser web-application by accessing the website using JWebUnit/htmlunit. To start the test:

(5) Go to commandline base-directory (where the pom.xml is)
(6) execute "mvn clean test"

Test should run

To check the stuff in Eclipse execute:

mvn eclipse:eclipse

and import the project into Eclipse.
