package at.ac.tuwien.ifs.bpse.core.test.persistence;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
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

import at.ac.tuwien.ifs.bpse.domain.Exam;
import at.ac.tuwien.ifs.bpse.persistence.SqlSessionFactoryBean;
import at.ac.tuwien.ifs.bpse.persistence.interfaces.ExamMapper;

public class ExamMapperTest {

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
	 * Data Access Object for Exam, fetched with ac. (Local/Method Scope)
	 */
	private ExamMapper ExamMapper;

	private static Logger log = Logger.getLogger(ExamMapperTest.class);

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
		ExamMapper = sqlSession.getMapper(ExamMapper.class);
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
	public void selectExam_shouldGetExamFromDB() {
		Exam e = (Exam)ac.getBean("ExamGet");
		Exam newe = ExamMapper.selectExam(e.getId());
		assertThat( newe.getLocation(), is(e.getLocation()) );
		
		assertThat(newe.getCourse(), is(notNullValue()));
	}
	
	@Test
	public void insertExam_shouldSaveExamToDB() {
		Exam e = (Exam)ac.getBean("ExamAdd");
		assertThat(e.getId(), is(510));
		
		//int id = s.getId();
		String location = e.getLocation();
		
		int ret = ExamMapper.insertExam(e);		
		assertThat(ret, is(1));
		assertThat(e.getId(), is(510));
		Exam newe = ExamMapper.selectExam(e.getId());
		
		assertThat( newe.getLocation(), is(location) );
	}
	
	@Test
	public void deleteExam_shouldDeleteExamFromDB() {
		Exam e = (Exam)ac.getBean("ExamUpdateDelete");
		int ret = ExamMapper.deleteExam(e.getId());
		log.info("DELETE Return Value: " + ret);
		Exam newe = ExamMapper.selectExam(e.getId());
		assertNull(newe);
	}
	
	@Test
	public void updateExam_shouldUpdateExamInDB() {
		Exam e = (Exam)ac.getBean("ExamUpdateDelete");
		int id = e.getId();
		String location = e.getLocation();
		
		int ret = ExamMapper.updateExam(e);
		log.info("UPDATE Return Value: " + ret);
		Exam newe = ExamMapper.selectExam(id);
		assertThat( newe.getLocation(), is(location) );
	}

}
