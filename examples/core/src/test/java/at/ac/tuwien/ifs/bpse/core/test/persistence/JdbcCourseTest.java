package at.ac.tuwien.ifs.bpse.core.test.persistence;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import at.ac.tuwien.ifs.bpse.dao.interfaces.ICourseDAO;
import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Professor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-beans.xml"})
public class JdbcCourseTest {

	/**
	 * Spring Framework Application Context (Bean Factory).
	 */
	@Autowired
	private ApplicationContext ac;
	
	/**
	 * Data Access Object for Course
	 */
	@Autowired
	private ICourseDAO courseDAO;
	
	
	/**
	 * Test StudentBean initialisation.
	 * 
	 */
	@Test
	public void beanInit_shouldLoadCourseFromSpringBeanConfig() {
		Course testCourse = (Course) ac.getBean("CourseGet");
		assertThat(testCourse, is(notNullValue()));
		assertThat(testCourse.getId(), is(2));
		assertThat(testCourse.getTitle(), is("SEPM"));
		assertThat(testCourse.getEcts(), is(Float.parseFloat("4.5")));
		List<Professor> testProfs = testCourse.getProfessors();
		assertThat(testProfs.size(), is(1));
	}
	
	@Test
	public void getCourse_shouldRetrieveCourseFromDB() {
		Course testCourse = (Course) ac.getBean("CourseGet");
		Course dbCourse = courseDAO.getCourse(testCourse.getId());
		assertThat(dbCourse.getId(), is(testCourse.getId()));
		assertThat(dbCourse.getTitle(), is(testCourse.getTitle()));
		assertThat(dbCourse.getEcts(), is(testCourse.getEcts()));
		assertThat(dbCourse.getTitle(), is(testCourse.getTitle()));
		assertThat(dbCourse.getProfessors().size(), is(1));
		Professor testProf = testCourse.getProfessors().get(0);
		Professor dbProf = dbCourse.getProfessors().get(0);
		assertThat(dbProf.getId(), is(testProf.getId()));
		assertThat(dbProf.getFirstname(), is (testProf.getFirstname()));
		assertThat(dbProf.getLastname(), is (testProf.getLastname()));
		assertThat(dbProf.getOfficenr(), is (testProf.getOfficenr()));
		assertThat(dbProf.getEmail(), is (testProf.getEmail()));
	}
	
	@Test
	public void saveCourse_shouldAddCourseToDB() {
		Course testCourse = (Course) ac.getBean("CourseAdd");
		final int oldId = testCourse.getId();
		final String title = testCourse.getTitle();
		final float ects = testCourse.getEcts();
		final List<Professor> profs = testCourse.getProfessors();
		
		Course dbCourse = courseDAO.saveCourse(testCourse);
		assertThat( dbCourse, is(notNullValue()) );
		// new ID should be different from old ID
		assertThat(dbCourse.getId(), is(not(oldId)));
		assertThat(dbCourse.getTitle(), is(title));
		assertThat(dbCourse.getEcts(), is(ects));
		assertThat(dbCourse.getProfessors().size(), is(profs.size()));
		
		Course dbCourse2 = courseDAO.getCourse(dbCourse.getId());
		assertThat(dbCourse2.getId(), is(not(oldId)));
		assertThat(dbCourse2.getTitle(), is(title));
		assertThat(dbCourse2.getEcts(), is(ects));
		assertThat(dbCourse2.getProfessors().size(), is(profs.size()));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void saveCourse_shouldNotBeAbleToAddCourseToDB() {
		Course testCourse = (Course) ac.getBean("CourseUnableToAdd");		
		courseDAO.saveCourse(testCourse);
	}
	
	@Test
	public void deleteCourse_shouldDeleteCourseFromDB() {
		// get a test-dataset from Spring config
		Course testCourse = (Course)ac.getBean("CourseDelete");
		// Delete Course
		boolean ret = courseDAO.deleteCourse(testCourse.getId());
		assertThat(ret, is(true));
		// check if Course was deleted
		// try to retrieve Course by ID again
		Course dbCourse = courseDAO.getCourse(testCourse.getId());
		// no dataset must be found (as it was deleted above)
		assertThat(dbCourse, is(nullValue()));
	}
	
	@Test
	public void updateCourse_shouldUpdateCourseInDB() {
		// get a test-dataset from Spring config
		Course testCourse = (Course)ac.getBean("CourseUpdate");
		final int id = testCourse.getId();
		final String title = testCourse.getTitle();
		final float ects = testCourse.getEcts();
		final List<Professor> profs = testCourse.getProfessors();
		
		// assuming this dataset already exists in the DB
		// and the dataset retrieved from test-beans.xml already contains slight changes to the data
		Course dbCourse = courseDAO.updateCourse(testCourse);
		assertThat(dbCourse, is(notNullValue()) );
		assertThat(dbCourse.getId(), is(id));
		assertThat(dbCourse.getTitle(), is(title));
		assertThat(dbCourse.getEcts(), is(ects));
		assertThat(dbCourse.getProfessors().size(), is(profs.size()));

		Course dbCourse2 = courseDAO.getCourse(dbCourse.getId());
		assertThat(dbCourse2.getId(), is(id));
		assertThat(dbCourse2.getTitle(), is(title));
		assertThat(dbCourse2.getEcts(), is(ects));
		assertThat(dbCourse2.getProfessors().size(), is(profs.size()));
	}
	
	@Test
	public void getCourseByTitle_shouldRetrieveCourseFromDB() {
		Course testCourse = (Course) ac.getBean("CourseGet");
		List<Course> dbCourses = courseDAO.getCourseByTitle(testCourse.getTitle());
		assertThat(dbCourses.size(), is(1));
		Course dbCourse = dbCourses.get(0);
		assertThat(dbCourse.getId(), is(testCourse.getId()));
		assertThat(dbCourse.getTitle(), is(testCourse.getTitle()));
		assertThat(dbCourse.getEcts(), is(testCourse.getEcts()));
		assertThat(dbCourse.getTitle(), is(testCourse.getTitle()));
		assertThat(dbCourse.getProfessors().size(), is(1));
		Professor testProf = testCourse.getProfessors().get(0);
		Professor dbProf = dbCourse.getProfessors().get(0);
		assertThat(dbProf.getId(), is(testProf.getId()));
		assertThat(dbProf.getFirstname(), is (testProf.getFirstname()));
		assertThat(dbProf.getLastname(), is (testProf.getLastname()));
		assertThat(dbProf.getOfficenr(), is (testProf.getOfficenr()));
		assertThat(dbProf.getEmail(), is (testProf.getEmail()));
	}

}
