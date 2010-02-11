package at.ac.tuwien.ifs.bpse.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Exam {
    @Id
    @GeneratedValue
    private int id;
    
    private Date exdate;
    private String location;

    @ManyToOne
    @JoinColumn(name="courseId")
    private Course course;
    
    public Exam(){}
    
    public Exam(Course course, Date exdate, String location){
        this.course = course;
        this.exdate = exdate;
        this.location = location;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getExdate() {
        return exdate;
    }

    public void setExdate(Date exDate) {
        this.exdate = exDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
