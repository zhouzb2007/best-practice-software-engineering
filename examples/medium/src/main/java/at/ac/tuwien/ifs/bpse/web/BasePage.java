package at.ac.tuwien.ifs.bpse.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import at.ac.tuwien.ifs.bpse.domain.Student;
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
public class BasePage extends WebPage {
	private Student loginStudent;
	
	/**
	 * The constructor always needs information about the
	 * actual logged in student
	 * @param student student
	 */
	public BasePage(Student student){
		loginStudent = student;
		add(new Label("studentName", new Model<String>(getStudentName())));
		
		add(new Link("linkToStudentProfile"){
			@Override
			public void onClick() {
				setResponsePage(new StudentPage(getLoginStudent()));
			}
		});
		add(new Link("linkToCourseList"){
			@Override
			public void onClick() {
				setResponsePage(new CourseListPage(getLoginStudent(), CourseListMode.REGISTRATION));
			}
		});
	}
	
	/**
	 * Return the logged in Student account
	 */
	private Student getLoginStudent(){
		return loginStudent;
	}
	
	/**
	 * Return the full name of the logged in student
	 */
	private String getStudentName(){
		if(getLoginStudent() != null){
			return getLoginStudent().getFirstname() + " " + getLoginStudent().getLastname();
		}else{
			return "";
		}
	}
}
