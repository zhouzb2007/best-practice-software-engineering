package at.ac.tuwien.ifs.bpse.services;

import org.apache.log4j.Logger;

import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * Default Implementation of the IStudentService interface
 * @author SE Team
 *
 */
public class StudentService implements IStudentService{
	private Logger log = Logger.getLogger(StudentService.class);

	@Override
	public Student login(String username, String password) {
		//TODO: Call dao to check login. 
		Student s = new Student();
		s.setId(1);
		s.setMatnr("0201857");
		s.setEmail("mdemolsky@gmail.com");
		s.setFirstname("Markus");
		s.setLastname("Demolsky");
		return s;
	}

	@Override
	public Student register(Student student) {
		log.info("Register new Student Account" + student.getMatnr() 
							+ ", " + student.getFirstname() 
							+ ", " + student.getLastname() 
							+ ", " + student.getEmail());
		//TODO: Call DAO
		return student;
	}

	@Override
	public void resetPassword(Student student) {
		log.info("Reset password for Student");
		//TODO: Implement
	}

	@Override
	public Student updateAccount(Student student) {
		if(student.getId() == 0){
			register(student);
			//TODO: Call DAO
		}else{
			log.info("Update Student Account" + student.getMatnr() 
					+ ", " + student.getFirstname() 
					+ ", " + student.getLastname() 
					+ ", " + student.getEmail());
			//TODO: Call DAO
		}
		return student;
	}

	@Override
	public boolean deleteStudent(int id) {
		//TODO: Call DAO
		return false;
	}

	@Override
	public Student getStudent(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudentByMatrNr(String matnr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
