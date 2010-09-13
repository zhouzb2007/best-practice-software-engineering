package at.ac.tuwien.ifs.bpse.web.course;

import java.util.List;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.web.BasePage;

/**
 * Course List page for registration
 * @author mde
 *
 */
public class CourseListPage extends BasePage {

	public CourseListPage(Student student, CourseListMode listMode){
		super();
		add(new CourseListPanel("courseListPanel", student, listMode));
	}
	
	public CourseListPage(Student student, List<Course> courses, CourseListMode listMode){
		super();
		add(new CourseListPanel("courseListPanel", courses, student, listMode));
	}

}
