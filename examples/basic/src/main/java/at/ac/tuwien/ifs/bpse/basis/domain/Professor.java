package at.ac.tuwien.ifs.bpse.basis.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Professor extends Person, hence inherits main properties from Person.
 */
public class Professor extends Person {
    private String officenr;
    
    private List<Course>courses =  new ArrayList<Course>();

    
    public Professor(){
        
    }
    public Professor(String officenr, String firstname, String lastname){
        setOfficenr(officenr);
        setFirstname(firstname);
        setLastname(lastname);
    }

    public String getOfficenr() {
        return officenr;
    }

    public void setOfficenr(String officenr) {
        this.officenr = officenr;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    
    public void addCourse(Course course){
        course.setProfessor(this);
        courses.add(course);
    }

}
