package at.ac.tuwien.ifs.bpse.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
@PrimaryKeyJoinColumn(name="CourseId")
/**
 * Lecture is extending Course, hence inherits all properties and methods from Course
 */
public class Lecture extends Course implements Serializable{
    
	private static final long serialVersionUID = 8321659568678554038L;
	private boolean compulsoryattendance;

    public Lecture(){}
    public Lecture(String title, int ecds, boolean compulsoryattendance){
        setTitle(title);
        setEcds(ecds);
        setCompulsoryattendance(compulsoryattendance);
    }
    
    public boolean isCompulsoryattendance() {
        return compulsoryattendance;
    }

    public void setCompulsoryattendance(boolean compulsoryattendance) {
        this.compulsoryattendance = compulsoryattendance;
    }

}
