package at.ac.tuwien.ifs.bpse.basis.domain;

/**
 * 
 * This class holds the data for one university course. 
 * Course has a connection to Professor, as each course is held by a professor
 * 
 * Several special type of courses can be inherited from this object (e.g., Lab and Lecture in this example)
 * 
 */
public class Course {
    private int id;

    private String title;
    private int ecds;
    
    /*
     * A professor can give more than one course, but each course is assigned to precisely one professor
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    

}
