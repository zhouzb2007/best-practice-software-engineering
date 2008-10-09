<!-- WELCOME LOGIN PAGE 
     The login page uses JSF-Components. Therefore it is necessary to import the html and 
     core tablibrary. The page enables internationalization by using message bundles. It 
     is important that no hardcoded text will be find on the page. Both labels and error messages
     are loaded from bundles. 
     Author: SE Team
-->
<%@ page contentType="text/html; charset=Cp1252" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
  <!-- INFO:
       Load customized messages from bundle. The messages are located in the messages_LOCALE.properties 
       file, which can be find in the classpath 
  -->
  <f:loadBundle basename="messages" var="msg"/>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=Cp1252"/>
		<title>Software Engineering Best Practices - Student Information System</title>
	</head>
	<body>
	    <!-- INFO: 
	         The view is the top level view. Every displaying component/information must be included within
	         a view. The form tag represents a HTML form in order to send/submit data to the server.
	    -->
			<f:view>
			<h:form>
			<!-- INFO: 
			     The input textfield uses a standard validator checking the input length. The matrikel number
			     of student must match the length 7. If a validation error occurs a corresponding error message
			     is shown done by h:message for.... The for attribute referes to the id of the inputText. This
			     is important, because the error message is now associated with the input field.
			-->
			<h:inputText value="#{loginController.matNr}" id="matnr" required="true">
				<h:outputLabel value="#{msg.lblMatnr}" for="matnr"/>
				<f:validateLength minimum="7" maximum="7"/>
			</h:inputText>
			<h:message for="matnr" errorStyle="color:red"></h:message>
			<!-- INFO:
			     The command button submits the form and starts the JSF-LifeCycle. If no error occurs (such as validation)
			     the method verifyStudent of the loginController bean is invoked. This is a case where dynamic navigation
			     is used. The return value of the method determines the next path of the page flow. The method is located
			     in at.ac.tuwien.ifs.qse.se1.web.controller.LoginController. In order to use the bean in a JSF application
			     it must be defined in the faces-config.xml as a managed-bean.
			-->
			<h:commandButton value="#{msg.btnLogin}" action="#{loginController.verifyStudent}"></h:commandButton>
			</h:form>
		</f:view>
	</body>
</html>

