<!-- COURSE LIST PAGE
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
			<h:form id="courseSearchForm">
				<t:div styleClass="top">
					<t:div id="header" forceId="true">
						<t:div id="headerbar" forceId="true">
							<t:htmlTag value="h1">
								<h:outputText value="#{msg.course_page_title}" />
							</t:htmlTag>
						</t:div>
					</t:div>
				</t:div>
				<t:div id="container" forceId="true">
					<%@ include file="includes/topnavigation.inc"%>
					<t:div id="main" forceId="true">
					    <%@ include file="includes/sidenavigation.inc"%>
						<t:div id="content" forceId="true">
							<t:htmlTag value="h1"><h:outputText value="#{msg.course_page_title}" /></t:htmlTag>
							<h:messages styleClass="messages" id="errorMessages" layout="list" showDetail="true" />
							<h:inputText value="#{courseController.searchString}" id="searchString">
							    <h:outputLabel value="#{msg.lblCourseName}" for="searchString"/>
							</h:inputText>
							<h:commandButton value="#{msg.btnCourseSearch}" action="#{courseController.search}"></h:commandButton>
							
							<t:htmlTag value="h2" rendered="#{courseController.searchResult}"><h:outputText value="#{msg.lblCourseSearchResults}" /></t:htmlTag>
							<t:htmlTag value="p" rendered="#{courseController.searchResult and courseController.noMatches}"><h:outputText value="#{msg.lblLecturesNoMatches}" /></t:htmlTag>
							<t:dataTable id="courseList"  rendered="#{courseController.searchResult}"
								value="#{courseController.result}"
								var="rec"
								border="1">
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderLID}"/>
									</f:facet>
									<h:outputText value="#{rec.id}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderName}"/>
									</f:facet>
									<h:outputText value="#{rec.title}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderECDS}"/>
									</f:facet>
									<h:outputText value="#{rec.ecds}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblHeaderProfessor}"/>
									</f:facet>
									<h:outputText value="#{rec.professor.lastname}" />
								</h:column>
								   <h:column>
							        <h:outputText value="#{row.employeeName}" />
							    </h:column>
								<h:column>
								    <t:commandLink action="showExams" immediate="true">
							            <h:outputText value="Exams" />
									    <t:updateActionListener property="#{course}" value="#{rec}"/>
								    </t:commandLink>
								</h:column>
							</t:dataTable>
						</t:div>
					</t:div>
				</t:div>
			</h:form>
		</t:documentBody>
	</t:document>

</f:view>
