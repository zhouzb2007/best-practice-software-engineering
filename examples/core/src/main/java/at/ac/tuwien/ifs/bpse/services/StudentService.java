package at.ac.tuwien.ifs.bpse.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import at.ac.tuwien.ifs.bpse.dao.interfaces.IStudentDAO;
import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * Default Implementation of the IStudentService interface
 * @author SE Team
 *
 */
public class StudentService implements IStudentService{
	private Logger log = Logger.getLogger(StudentService.class);

	/**
	 * Data Access Object for Student
	 */
	@Autowired
	private IStudentDAO studentDAO;
	
	@Autowired
	private JdbcUserDetailsManager userDAO;
	
	@Override
	public Student getStudentByUsername(String username) {
		log.info("Get Student for Username: " + username);
		// Call DAO to get Student BO. 
		Student s = studentDAO.getStudentByEmail(username);
		return s;
	}

	@Override
	public Student register(Student student, UserDetails user) {
		log.info("Register new Student Account" + student.getMatnr() 
							+ ", " + student.getFirstname() 
							+ ", " + student.getLastname() 
							+ ", " + student.getEmail());
		// register Student as a User, add User priviledges
		userDAO.createUser(user);
		// add Student profile
		studentDAO.saveStudent(student);
		return student;
	}

	@Override
	public void resetPassword(UserDetails user) {
		/*
		log.info("Reset password for Student "+user.getUsername());
		if (user.getPassword()==null)
			// reset pw and send email
		else if (userDAO.userExists(user.getUsername()))
			userDAO.changePassword(oldPassword, newPassword);
			*/
	}

	@Override
	public Student updateAccount(Student student) {
		log.info("Update Student Account" + student.getMatnr() 
				+ ", " + student.getFirstname() 
				+ ", " + student.getLastname() 
				+ ", " + student.getEmail());
		studentDAO.updateStudent(student);
		return student;
	}

	@Override
	public boolean deleteStudent(int id) {
		// TODO: set user inactive
		return studentDAO.deleteStudent(id);
	}

	@Override
	public Student getStudent(int id) {
		return studentDAO.getStudent(id);
	}

	@Override
	public Student getStudentByMatrNr(String matnr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student saveStudent(Student student) {
		return studentDAO.saveStudent(student);
	}

	@Override
	public Student updateStudent(Student student) {
		return studentDAO.updateStudent(student);
	}
	
	

}
