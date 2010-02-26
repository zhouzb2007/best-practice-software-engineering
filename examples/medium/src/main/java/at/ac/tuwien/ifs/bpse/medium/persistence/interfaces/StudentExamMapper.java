package at.ac.tuwien.ifs.bpse.medium.persistence.interfaces;

import at.ac.tuwien.ifs.bpse.domain.StudentExam;

public interface StudentExamMapper {
	
	public StudentExam selectStudentExam(int id);
	public StudentExam selectExamByStudent(int id);
	public StudentExam selectExamByTutor(int id);
	
	public int insertStudentExam(StudentExam se);
	
	public int updateStudentExam(StudentExam se);
	
	public int deleteStudentExam(int id);
	
}
