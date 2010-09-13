package at.ac.tuwien.ifs.bpse.web;

import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.services.IStudentService;
import at.ac.tuwien.ifs.bpse.web.course.CourseListMode;
import at.ac.tuwien.ifs.bpse.web.course.CourseListPage;
import at.ac.tuwien.ifs.bpse.web.student.StudentPage;

/**
 * The Base Page should be used by all other
 * Web Pages. The base page provides the general
 * container and information, such as header, menus
 * and other stuff
 * @author mde
 *
 */
@AuthorizeInstantiation("ROLE_USER")
public class BasePage extends WebPage {
	private Student loginStudent;
	
	@SpringBean
	private IStudentService studserv;
	
	/**
	 * The constructor always needs information about the
	 * actual logged in student
	 * @param student student
	 */
	public BasePage(){
		loginStudent = studserv.getStudentByUsername(getLoginName());
		
		add(new Label("studentName", loginStudent.getFullname()));
		
		add(new Link("linkToStudentProfile"){
			@Override
			public void onClick() {
				setResponsePage(new StudentPage(loginStudent));
			}
		});
		add(new Link("linkToCourseList"){
			@Override
			public void onClick() {
				setResponsePage(new CourseListPage(loginStudent, CourseListMode.REGISTRATION));
			}
		});
	}
	
	/**
	 * Return the logged in account
	 */
	private String getLoginName(){
		PropertyModel<String> pm = new PropertyModel<String>(this, "session.user");
		return pm.getObject();
	}
	
}
