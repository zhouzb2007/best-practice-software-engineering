<!-- Professor LIST PAGE
     Author: SE Team
-->
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
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
			<h:form id="professorSearchForm">
				<t:div styleClass="top">
					<t:div id="header" forceId="true">
						<t:div id="headerbar" forceId="true">
							<t:htmlTag value="h1">
								<h:outputText value="#{msg.professor_page_title}" />
							</t:htmlTag>
						</t:div>
					</t:div>
				</t:div>
	
				<t:div id="container" forceId="true">
					<%@ include file="includes/topnavigation.inc"%>
					<t:div id="main" forceId="true">
					    <%@ include file="includes/sidenavigation.inc"%>
						<t:div id="content" forceId="true">
							<t:htmlTag value="h1"><h:outputText value="#{msg.professor_page_title}" /></t:htmlTag>
							<h:messages styleClass="messages" id="errorMessages" layout="list" showDetail="true" />
							<h:inputText value="#{professorController.searchString}" id="searchString" required="true">
    							<h:outputLabel value="#{msg.lblProfessorName}" for="searchString"/>
							</h:inputText>
							<h:commandButton value="#{msg.btnProfessorSearch}" action="#{professorController.search}"></h:commandButton>
							
							<t:htmlTag value="h2" rendered="#{professorController.searchResult}"><h:outputText value="#{msg.lblProfessorSearchResults}" /></t:htmlTag>
							<t:htmlTag value="p" rendered="#{professorController.searchResult and professorController.noMatches}"><h:outputText value="#{msg.lblLecturesNoMatches}" /></t:htmlTag>
							<h:dataTable id="courseList"  rendered="#{professorController.searchResult}"
								value="#{professorController.result}"
								var="professor"
								border="1">
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderOffice}"/>
									</f:facet>
									<h:outputText value="#{professor.officenr}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderPID}"/>
									</f:facet>
									<h:outputText value="#{professor.id}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderFirstName}"/>
									</f:facet>
									<h:outputText value="#{professor.firstname}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderLastName}"/>
									</f:facet>
									<h:outputText value="#{professor.lastname}" />
								</h:column>
							</h:dataTable>
						</t:div>
					</t:div>
				</t:div>
			</h:form>
		</t:documentBody>
	</t:document>

</f:view>
