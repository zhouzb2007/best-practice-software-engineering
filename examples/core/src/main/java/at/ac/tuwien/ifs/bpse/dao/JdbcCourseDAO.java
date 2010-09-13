package at.ac.tuwien.ifs.bpse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

import at.ac.tuwien.ifs.bpse.dao.interfaces.ICourseDAO;
import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Exam;
import at.ac.tuwien.ifs.bpse.domain.Professor;


/**
 * Implementation of the Course Data Access Object for JDBC. This class makes
 * heavy use of the Spring Framework's facilities and provides access to the
 * data stored in the database. It also defines how this data is mapped from
 * the application objects to the database tables and vice-versa.
 * 
 * @author The SE-Team
 * @version 2.1
 */
public class JdbcCourseDAO implements ICourseDAO {

	/**
	 * Retrieves the logger for this class.
	 */
	private static Log log = LogFactory.getLog(JdbcCourseDAO.class);

	/**
	 * Provides access to the Datasource, set by setDataSource().
	 * Wrapper class for JdbcTemplate providing Java-5-based convenience and exposing
	 * only the most commonly required operations.
	 */
	protected SimpleJdbcTemplate simpleJdbcTemplate = null;

	/**
	 * Transaction Manager. For encapsulating insert and updates in transaction.
	 */
	private PlatformTransactionManager transactionManager = null;

	/**
	 * SQL Query Strings.
	 */
	private String sql_selectAllCourses = "";
	private String sql_selectProfessorsToCourse = "";
	private String sql_selectCourse = "";
	private String sql_insertCourse = "";
	private String sql_getInsertId = "";
	private String sql_selectCourseByTitle = "";
	private String sql_selectExamsOfCourse = "";
	private String sql_updateCourse = "";
	private String sql_deleteCourse = "";
	private String sql_insertCourseProfessors = "";
	private String sql_deleteAllCourseProfessors = "";
	
	/** ******************************************************************* */
	/** ******************************************************************* */
	/* C O N S T R U C T O R */
	/** ******************************************************************* */
	/** ******************************************************************* */

	public JdbcCourseDAO() {
		super();
	}

	/**
	 * The Initialise Method must be called after all bean values are set,
	 * particularly the datasource and the transaction manager. This is actually
	 * performed by the Spring Framework, which sets first of all, all Java Bean
	 * Properties and eventually calls this init method (see bean definition in
	 * beans.xml configuration file)
	 */
	public void init() {
		log.info("Initialise CourseDAO");
	}

	/**
	 * The Destroy Method is called by the Spring Framework to end the lifecycle
	 * of this bean, but <b>only</b> when the bean is created as singleton.
	 * Check the bean definition in beans.xml configuration file for details.
	 */
	public void destroy() {
		log.info("Destroy CourseDAO");
	}

	/** ******************************************************************* */
	/** ******************************************************************* */
	/*
	 * BEAN SETTERS FOR DEPENDENCY INJECTION
	 * 
	 * Dependency Injection is a design pattern in which the responsibility for
	 * object creation and object linking is removed from the objects themselves
	 * and transferred to a factory object. It is a way to achieve loose
	 * coupling between objects and results in highly testable objects
	 * (controlled unit tests).
	 * 
	 * Factory Object: (Design Pattern) is an object for creating other objects.
	 * Typically a factory has a method for every kind of object it is capable
	 * of creating. these methods optionally accept parameters defining how the
	 * object is created and then return the created object.
	 */
	/** ******************************************************************* */
	/** ******************************************************************* */



	public void setSql_selectAllCourses(String sql_selectAllCourses) {
		this.sql_selectAllCourses = sql_selectAllCourses;
	}

	public void setSql_selectCourse(String sql_selectCourse) {
		this.sql_selectCourse = sql_selectCourse;
	}
	
	public void setSql_selectProfessorsToCourse(String sql_selectProfessorsToCourse) {
		this.sql_selectProfessorsToCourse = sql_selectProfessorsToCourse;
	}

	public void setSql_insertCourse(String sql_insertCourse) {
		this.sql_insertCourse = sql_insertCourse;
	}

	public void setSql_getInsertId(String sql_getInsertId) {
		this.sql_getInsertId = sql_getInsertId;
	}

	public void setSql_selectCourseByTitle(String sql_selectCourseByTitle) {
		this.sql_selectCourseByTitle = sql_selectCourseByTitle;
	}

	public void setSql_selectExamsOfCourse(String sql_selectExamsOfCourse) {
		this.sql_selectExamsOfCourse = sql_selectExamsOfCourse;
	}

	public void setSql_updateCourse(String sql_updateCourse) {
		this.sql_updateCourse = sql_updateCourse;
	}

	public void setSql_deleteCourse(String sql_deleteCourse) {
		this.sql_deleteCourse = sql_deleteCourse;
	}

	public void setSql_insertCourseProfessors(String sql_insertCourseProfessors) {
		this.sql_insertCourseProfessors = sql_insertCourseProfessors;
	}
	
	public void setSql_deleteAllCourseProfessors(String sql_deleteAllCourseProfessors) {
		this.sql_deleteAllCourseProfessors = sql_deleteAllCourseProfessors;
	}
	
	
	/**
	 * Sets the Datasource to connect to database.
	 * 
	 * @param dataSource
	 *            SQL Datasource
	 * @see #dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	/**
	 * Sets the transaction manager.
	 * 
	 * @param transactionManager
	 *            central interface in Spring's transaction infrastructure
	 * @see #transactionManager
	 */
	public void setTransactionManager(
			PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	/**
	 * Maps data from a database row to an application object.
	 * This method is used by all DAO methods which query the database 
	 * with an SQL SELECT statement.
	 *
	 * @since 2.1
	 *
	 */
	public final class CourseMapper implements RowMapper<Course> {
	      public Course mapRow(ResultSet rs, int rowNumber)
			throws SQLException {
	    	    Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setTitle(rs.getString("title"));
				course.setEcts(rs.getFloat("ects"));
				List<Professor> profs = simpleJdbcTemplate.query(sql_selectProfessorsToCourse, new ProfessorMapper(), course.getId());
				course.setProfessors(profs);
				return course;
			}
	  }
	
	public static final class ProfessorMapper implements RowMapper<Professor> {
		public Professor mapRow(ResultSet rs, int rowNumber)
				throws SQLException {
			Professor p = new Professor(rs.getString("firstname"),
					rs.getString("lastname"), rs.getString("email"),
					rs.getString("officenr"));
			p.setId(rs.getInt("id"));
			return p;
		}
	}

	@Override
	public Course getCourse(int id) {
		log.info("Get Course ID = " + id);
		
		List<Course> courses = simpleJdbcTemplate.query(sql_selectCourse, new CourseMapper(), id);
		if (courses.size() == 1) {
			Course c = courses.get(0);
			log.debug("Returning Course \"" + c.getTitle() + "\"");
			return c;
		} else {
			log.debug("No Course data");
			return null;
		}
	}

	@Override
	public Course saveCourse(Course course) {
		log.info("Add Course Title = " + course.getTitle());
		log.debug("Insert Course");
		simpleJdbcTemplate.update(sql_insertCourse, course.getTitle(),course.getEcts());
		Integer id = simpleJdbcTemplate.queryForInt(sql_getInsertId);
		course.setId(id);
		log.info("Return ID from inserted dataset = " + id);
		log.debug("Now insert Course references to Professor");
		for (Professor p: course.getProfessors()) {
			simpleJdbcTemplate.update(sql_insertCourseProfessors, course.getId(),p.getId());
		}
		return (id!=0) ? course : null;
	}

	@Override
	public Course updateCourse(Course course) {
		log.info("Update Course, ID = " + course.getId() + " Title = "
				+ course.getTitle());
		log.debug("Execute update");
		if (simpleJdbcTemplate.update(sql_updateCourse, course.getTitle(),
				course.getEcts(), course.getId()) == 1) {
			log.debug("Update Course Successfull");
			simpleJdbcTemplate.update(sql_deleteAllCourseProfessors, course.getId());
			log.debug("Now inserting Course references for Professors");
			for (Professor p : course.getProfessors()) {
				simpleJdbcTemplate.update(sql_insertCourseProfessors,
						course.getId(), p.getId());
			}

			return course;
		}
		log.error("Update for Course ID = " + course.getId() + " failed.");
		return null;
	}

	@Override
	public boolean deleteCourse(int id) {
		log.info("Delete Course ID = " + id);
		log.debug("Initialize SQL Parameters");
		final Object[] param = new Object[] { id };
		log.debug("Executing SQL");
		if (simpleJdbcTemplate.update(sql_deleteCourse, param) == 1) {
			log.debug("Deletion successfull");
			return true;
		}
		log.error("Deleting Course ID = " + id + " failed");
		return false;
	}

	@Override
	public List<Course> getCourseByTitle(String title) {
		log.info("Get Course by Title = " + title);
		
		List<Course> courses = simpleJdbcTemplate.query(sql_selectCourseByTitle, new CourseMapper(), title);
		if (courses.size() == 1) {
			Course c = courses.get(0);
			log.debug("Returning Course \"" + c.getTitle() + "\"");
			return courses;
		} else {
			log.debug("Returning several found Courses");
			return courses;
		}
	}

	@Override
	public List<Exam> getExamsOfCourse(Course course) {
		log.info("Get Exams of Course = " + course.getTitle());
		return null;
	} 

	

}