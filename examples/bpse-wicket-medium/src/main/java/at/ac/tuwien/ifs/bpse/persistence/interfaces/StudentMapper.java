package at.ac.tuwien.ifs.bpse.persistence.interfaces;

import at.ac.tuwien.ifs.bpse.domain.Student;

public interface StudentMapper {
	
	// We could also use Annotations here:
	//@Select("select * from students where id = #{id}")
	public Student selectStudent(int id);
	
	public Student selectStudentByMatrNr(String matnr);
	
	public int insertStudent(Student s);
	
	public int updateStudent(Student s);
	
	public int deleteStudent(int id);
	
}
