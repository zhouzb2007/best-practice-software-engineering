package at.ac.tuwien.ifs.bpse.persistence.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import at.ac.tuwien.ifs.bpse.dao.interfaces.IStudentDAO.SortOrder;
import at.ac.tuwien.ifs.bpse.domain.Student;

public interface StudentMapper {
	
	// We could also use Annotations here:
	//@Select("select * from students where id = #{id}")
	public Student selectStudent(int id);
	
	public List<Student> selectAllStudents(@Param("order") SortOrder order);

	public List<Student> selectAllStudents();

	public Student selectStudentByMatrNr(String matnr);
	
	public int insertStudent(Student s);
	
	public int updateStudent(Student s);
	
	public int deleteStudent(int id);
	
	public List<Student> findStudents(@Param("searchphrase") String searchphrase, @Param("order") SortOrder order);
	
	public List<Student> findStudents(@Param("searchphrase") String searchphrase);
}
