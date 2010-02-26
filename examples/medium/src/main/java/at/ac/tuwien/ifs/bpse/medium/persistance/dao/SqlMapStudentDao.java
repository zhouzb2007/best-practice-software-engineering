package at.ac.tuwien.ifs.bpse.medium.persistance.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.ibatis.session.SqlSession;

import at.ac.tuwien.ifs.bpse.dao.IStudentDAO;
import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.domain.StudentExam;
import at.ac.tuwien.ifs.bpse.medium.persistence.SqlSessionFactoryBean;
import at.ac.tuwien.ifs.bpse.medium.persistence.interfaces.StudentMapper;

public class SqlMapStudentDao implements IStudentDAO {

	/**
	 * SessionFactory is a Spring managed Singleton. (Local Scope)
	 */
	SqlSessionFactoryBean sqlSessionFactory;

	/**
	 * (Local/Class Scope)
	 */
	SqlSession sqlSession;
	
	/**
	 * Data Access Object for Student, fetched with ac. (Local/Method Scope)
	 */
	private StudentMapper studentMapper;
	
	public void setSqlSessionFactory(SqlSessionFactoryBean sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@PostConstruct
	public void buildDao() {
		sqlSession = sqlSessionFactory.openSession();
		studentMapper = sqlSession.getMapper(StudentMapper.class);
	}
	
    @PreDestroy
    public void cleanDao() {
    	sqlSession.close();
    }
	
	public boolean deleteStudent(int id) {
		int ret = studentMapper.deleteStudent(id);
		if (ret==1)
			return true;
		else
			return false;
	}

	public List<StudentExam> getRegisteredExams(Student arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Student getStudent(int id) {
		return studentMapper.selectStudent(id);
	}

	public Student getStudentByMatrNr(String matnr) {
		return studentMapper.selectStudentByMatrNr(matnr);
	}

	public List<Student> getStudents(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public Student saveStudent(Student s) {
		int ret = studentMapper.insertStudent(s);
		if (ret==1)
			return s;
		else
			return null;
	}

	public Student updateStudent(Student s) {
		int ret = studentMapper.updateStudent(s);
		if (ret==1)
			return s;
		else
			return null;
	}

}
