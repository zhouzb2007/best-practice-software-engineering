<!-- COURSE EXAM LIST PAGE
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
    	    <h:form>
				<t:div styleClass="top">
					<t:div id="header" forceId="true">
						<t:div id="headerbar" forceId="true">
							<t:htmlTag value="h1">
								<h:outputText value="#{msg.courseExamList_page_title}" />
							</t:htmlTag>
						</t:div>
					</t:div>
				</t:div>
				<t:div id="container" forceId="true">
					<%@ include file="includes/topnavigation.inc"%>
					<t:div id="main" forceId="true">
					    <%@ include file="includes/sidenavigation.inc"%>
						<t:div id="content" forceId="true">
							<h:outputText value="#{msg.examRegisterSuccess}" rendered="#{serviceController.registerSuccess}"/>
							<t:dataTable id="courseExamList"  rendered="#{courseController.searchResult}"
								value="#{serviceController.examsForSelectedCourse}"
								var="rec"
								border="1">
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblExamId}"/>
									</f:facet>
									<h:outputText value="#{rec.id}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblExamDate}"/>
									</f:facet>
									<h:outputText value="#{rec.exdate}" />
								</h:column>
								<h:column>
									<f:facet name="header">
										<h:outputText value="#{msg.tblExamLocation}"/>
									</f:facet>
									<h:outputText value="#{rec.location}" />
								</h:column>
								<h:column>
								    <t:commandLink action="#{serviceController.doExamRegistration}" immediate="true">
							            <h:outputText value="Register" />
									    <t:updateActionListener property="#{exam}" value="#{rec}"/>
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
