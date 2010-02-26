package at.ac.tuwien.ifs.bpse.medium.persistence.interfaces;

import at.ac.tuwien.ifs.bpse.domain.Course;

public interface CourseMapper {
	
	public Course selectCourse(int id);
	
	public int insertCourse(Course c);
	
	public int updateCourse(Course c);
	
	public int deleteCourse(int id);
	
}
