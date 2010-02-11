<t:div id="sidenav" forceId="true">
	<t:htmlTag value="h2" id="sidenav_navigation_head"><h:outputText value="#{msg.sidenav_navigation_title}"/></t:htmlTag>
	<t:panelNavigation2 id="side_navigations" layout="list">
		<t:commandNavigation2 value="#{msg.main_courseList}" action="courseList" />
		<t:commandNavigation2  value="#{msg.main_professorList}" action="professorList" />
		<t:commandNavigation2 value="#{msg.main_examList}" action="examList" />
	</t:panelNavigation2>
</t:div>