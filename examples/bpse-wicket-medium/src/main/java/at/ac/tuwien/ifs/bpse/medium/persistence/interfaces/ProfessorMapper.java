package at.ac.tuwien.ifs.bpse.medium.persistence.interfaces;

import at.ac.tuwien.ifs.bpse.domain.Professor;

public interface ProfessorMapper {
	
	public Professor selectProfessor(int id);
	
	public int insertProfessor(Professor p);
	
	public int updateProfessor(Professor p);
	
	public int deleteProfessor(int id);

	public Professor selectProfessorByOfficeNr(String officenr);
	
}
