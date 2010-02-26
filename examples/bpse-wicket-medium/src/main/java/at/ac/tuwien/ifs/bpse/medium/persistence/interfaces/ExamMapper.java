package at.ac.tuwien.ifs.bpse.medium.persistence.interfaces;

import at.ac.tuwien.ifs.bpse.domain.Exam;

public interface ExamMapper {
	
	public Exam selectExam(int id);
	
	public int insertExam(Exam e);
	
	public int updateExam(Exam e);
	
	public int deleteExam(int id);
	
}
