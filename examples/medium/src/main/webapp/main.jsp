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
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<f:loadBundle basename="messages" var="msg" />

<f:view>
	<t:document>
		<t:documentHead>
			<t:htmlTag value="title">
				<h:outputText value="#{msg.app_title}" />
			</t:htmlTag>
			<t:stylesheet path="#{msg.template}" media="screen" />
		</t:documentHead>
		<t:documentBody>
			<h:form id="indexForm">	
				<t:div styleClass="top">
					<t:div id="header" forceId="true">
						<t:div id="headerbar" forceId="true">
							<t:htmlTag value="h1">
							<h:outputText value="#{msg.main_welcome}"></h:outputText>
							<h:outputText value="#{sessionStudent.fullname}"></h:outputText>
							</t:htmlTag>
						</t:div>
					</t:div>
				</t:div>
				<t:div id="container" forceId="true">
	
					<%@ include file="includes/topnavigation.inc"%>
	
					<t:div id="main" forceId="true">
	
						<%@ include file="includes/sidenavigation.inc"%>
	
						<t:div id="content" forceId="true">
							<t:htmlTag value="h1"><h:outputText value="#{msg.app_welcome_title}" /></t:htmlTag>
							<t:htmlTag value="p"><h:outputText value="#{msg.app_welcome_text}" /></t:htmlTag>
							<t:htmlTag value="p" id="app_login_description" forceId="true" rendered="#{!student.loggedIn}"><h:outputText value="#{msg.app_login_description}" /></t:htmlTag>
							<t:htmlTag value="p" id="app_loggedIn_description" forceId="true" rendered="#{student.loggedIn}"><h:outputText value="#{msg.app_loggedIn_description}" /></t:htmlTag>
						</t:div>
					</t:div>
					<t:div id="footer" forceId="true">&copy; <h:outputText value="#{msg.footer}" /></t:div>
				</t:div>
			</h:form>
		</t:documentBody>
	</t:document>

</f:view>