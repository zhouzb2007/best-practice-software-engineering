package at.ac.tuwien.ifs.bpse.scratchpad.model;

public class Course {

	private String title;
	private int ecds;
	private Professor professor;
	
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	public int getEcds() {
		return ecds;
	}
	public void setEcds(int ecds) {
		this.ecds = ecds;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
