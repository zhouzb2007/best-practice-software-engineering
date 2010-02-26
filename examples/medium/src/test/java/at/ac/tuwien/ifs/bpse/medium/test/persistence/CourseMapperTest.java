package at.ac.tuwien.ifs.bpse.medium.test.persistence;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.medium.persistence.SqlSessionFactoryBean;
import at.ac.tuwien.ifs.bpse.medium.persistence.interfaces.CourseMapper;

public class CourseMapperTest {

	/**
	 * Spring Framework Application Context (Bean Factory, Global Scope).
	 */
	private static ApplicationContext ac; 
	
	/**
	 * SessionFactory is a Spring managed Singleton. (Local Scope)
	 */
	SqlSessionFactoryBean sqlSessionFactory;
	
	/**
	 * (Local/Class Scope)
	 */
	SqlSession sqlSession;
	
	/**
	 * Data Access Object for Course, fetched with ac. (Local/Method Scope)
	 */
	private CourseMapper CourseMapper;

	private static Logger log = Logger.getLogger(CourseMapperTest.class);

	private Connection conn;

	private ScriptRunner runner;
	
	private final String database = "jdbc:hsqldb:mem:bpse-sample-medium";
	
	private final String dbUser = "sa";
	
	private final String dbPassword = "";
	
	private final String dbSchema = "bpse-sample-medium-TestSchema.sql";
	
	private final String destroySchema = "bpse-sample-medium-destroySchema.sql";
	
	private final String dbData = "bpse-sample-medium-TestData.sql";
	
	@BeforeClass
	public static void setUpBeforeClass() {
		// notice, that the TEST beans.xml is loaded!
		ac = new FileSystemXmlApplicationContext("classpath:test-beans.xml");
	}
	
	@Before
	public void setUp() {
		try {
			conn = DriverManager.getConnection(database, dbUser, dbPassword);
			runner = new ScriptRunner(conn);
			runner.runScript(Resources.getResourceAsReader(dbSchema));
			runner.runScript(Resources.getResourceAsReader(dbData));
		} catch (SQLException e) {
			log.info("Problem initializing DB connection " + database
					+ " with user " + dbUser + " and password " + dbPassword);
			e.printStackTrace();
		} catch (IOException e) {
			log.info("DB Script " + dbData + " not found");
			e.printStackTrace();
		}
		
		sqlSessionFactory = (SqlSessionFactoryBean) ac.getBean("sqlSessionFactory");
		sqlSession = sqlSessionFactory.openSession();
		CourseMapper = sqlSession.getMapper(CourseMapper.class);
	}

	@After
	public void tearDown() {
		log.info("Test done, closing sqlSession");
		sqlSession.close();
		
		try {
			conn = DriverManager.getConnection(database, dbUser, dbPassword);
			runner = new ScriptRunner(conn);
			runner.runScript(Resources.getResourceAsReader(destroySchema));
		} catch (SQLException e) {
			log.info("Problem initializing DB connection " + database
					+ " with user " + dbUser + " and password " + dbPassword);
			e.printStackTrace();
		} catch (IOException e) {
			log.info("DB Script " + destroySchema + " not found");
			e.printStackTrace();
		}
	}

	@Test
	public void selectCourse_shouldGetCourseFromDB() {
		Course c = (Course)ac.getBean("CourseGet");
		Course newc = CourseMapper.selectCourse(c.getId());
		assertThat( newc.getTitle(), is(c.getTitle()) );
		
		assertThat (newc.getProfessors().get(0).getFirstname(), is("Assi"));
	}
	
	@Test
	public void insertCourse_shouldSaveCourseToDB() {
		Course c = (Course)ac.getBean("CourseAdd");
		assertThat(c.getId(), is(509));
		
		//int id = s.getId();
		String title = c.getTitle();
		
		int ret = CourseMapper.insertCourse(c);		
		assertThat(ret, is(1));
		assertThat(c.getId(), is(3));
		Course newc = CourseMapper.selectCourse(c.getId());
		
		assertThat( newc.getTitle(), is(title) );
	}
	
	@Test
	public void deleteCourse_shouldDeleteCourseFromDB() {
		Course c = (Course)ac.getBean("CourseUpdateDelete");
		int ret = CourseMapper.deleteCourse(c.getId());
		log.info("DELETE Return Value: " + ret);
		Course newc = CourseMapper.selectCourse(c.getId());
		assertNull(newc);
	}
	
	@Test
	public void updateCourse_shouldUpdateCourseInDB() {
		Course c = (Course)ac.getBean("CourseUpdateDelete");
		int id = c.getId();
		String title = c.getTitle();
		
		int ret = CourseMapper.updateCourse(c);
		log.info("UPDATE Return Value: " + ret);
		Course newc = CourseMapper.selectCourse(id);
		assertThat( newc.getTitle(), is(title) );
	}

}
