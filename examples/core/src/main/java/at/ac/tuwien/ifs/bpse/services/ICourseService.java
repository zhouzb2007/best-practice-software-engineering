package at.ac.tuwien.ifs.bpse.services;

import java.util.List;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * ICourseService interface
 * @author mde
 *
 */
public interface ICourseService {
	
	/**
	 * Load a list of courses
	 * @param name of the course (acts as filter)
	 * @return list of courses. If no courses found, an empty list will be returned
	 */
	public List<Course> getCoursesByName(String name);
	
	/**
	 * Register a student to the selected course
	 * @param student
	 * @param course
	 * @return true if registration was successfull, otherwise false
	 */
	public boolean registerToCourse(Student student, Course course);
	
	/**
	 * Unregister the student from the selected course
	 * @param student student
	 * @param course course to be unregister
	 * @return true if unregistration was successfull, otherwise false
	 */
	public boolean unregisterFromCourse(Student student, Course course);
	
	/**
	 * Load a list of courses, but only for the student
	 * @param name of the course (acts as filter)
	 * @param student student
	 * @return list of courses. If no courses found, an empty list will be returned
	 */
	public List<Course> getCoursesForStudent(String name, Student student);
	
	/**
	 * Save course
	 * @param course course
	 * @return the persisted/updated course object 
	 */
	public Course saveCourse(Course course);

	/**
	 * Remove course
	 * @param course course
	 * @return true if course was succcessfully removed, otherwise false
	 */
	public boolean removeCourse(Course course);
}
