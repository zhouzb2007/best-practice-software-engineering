package at.ac.tuwien.ifs.bpse.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import at.ac.tuwien.ifs.bpse.dao.interfaces.ICourseDAO;
import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * Default implementation of the Course Service
 * @author SE Team
 *
 */
public class CourseService implements ICourseService {
	private Logger log = Logger.getLogger(CourseService.class);

	/**
	 * Data Access Object for Course
	 */
	@Autowired
	private ICourseDAO courseDAO;
	
	@Override
	public List<Course> getCoursesByName(String name) {
		log.info("Search for courses by name: " + name);
		return courseDAO.getCourseByTitle(name);
	}

	@Override
	public boolean registerToCourse(Student student, Course course) {
		log.info("Register Student " + student.getFullname() + " for course: " + course.getTitle());
		return false;
	}

	@Override
	public boolean unregisterFromCourse(Student student, Course course) {
		log.info("Unregister Student " + student.getFullname() + " for course: " + course.getTitle());
		return false;
	}

	@Override
	public List<Course> getCoursesForStudent(String name, Student student) {
		log.info("Search for courses by name: " + name + " only for student " + student.getFullname());
		Course c1 = new Course();
		c1.setId(1);
		c1.setTitle("Course 1");
		Course c2 = new Course();
		c2.setId(1);
		c2.setTitle("Course 2");
		
		List<Course> courses = new ArrayList<Course>();
		courses.add(c1);
		courses.add(c2);
		
		return courses;
	}

	@Override
	public Course saveCourse(Course course) {
		log.info("Save courses " + course.getTitle() + " ," + course.getEcts());
		return courseDAO.saveCourse(course);
	}

	@Override
	public boolean removeCourse(Course course) {
		log.info("Remove courses " + course.getTitle() + " ," + course.getEcts());
		return courseDAO.deleteCourse(course.getId());
	}

}
