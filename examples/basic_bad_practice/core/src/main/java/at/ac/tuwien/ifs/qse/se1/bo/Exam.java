package at.ac.tuwien.ifs.qse.se1.bo;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO (Plain Old Java Object) Representing the internal state of an exam.
 * @author The SE-Team
 * @version 1.0
 */
@Entity
@Table(name = "EXAMS")
public class Exam {
    @Id
    @GeneratedValue(generator = "exam-id")
    @GenericGenerator(name = "exam-id", strategy = "increment")
    private long id;
    @OneToMany(targetEntity = at.ac.tuwien.ifs.qse.se1.bo.Student.class, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "EXAM_STUDENTS", joinColumns = { @JoinColumn(name = "EXAM_ID") }, inverseJoinColumns = { @JoinColumn(name = "STUDENT_ID") })
    private Set<Student> students;
    @ManyToOne
    @JoinColumn(name = "LECTURE_FK")
    private Lecture lecture;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "LOCATION")
    private String location;

    public Date getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
