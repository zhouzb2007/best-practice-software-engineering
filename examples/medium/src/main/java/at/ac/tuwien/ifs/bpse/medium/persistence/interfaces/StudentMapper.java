package at.ac.tuwien.ifs.bpse.medium.persistence.interfaces;

import java.util.List;

import at.ac.tuwien.ifs.bpse.domain.Student;

public interface StudentMapper {
	
	// We could also use Annotations here:
	//@Select("select * from students where id = #{id}")
	public Student selectStudent(int id);
	
	public Student selectStudentByMatrNr(String matnr);
	
	public int insertStudent(Student s);
	
	public int updateStudent(Student s);
	
	public int deleteStudent(int id);
	
	public List<Student> findStudents(String searchphrase);
	
}
