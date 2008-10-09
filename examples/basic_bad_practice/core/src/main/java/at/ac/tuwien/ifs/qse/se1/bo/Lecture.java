package at.ac.tuwien.ifs.qse.se1.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO (Plain Old Java Object) Representing the internal state of a Lecture. This Class
 * encapsulates all Datafields and Methods needed for a Lecture.
 * @author The SE-Team
 * @version 1.0
 */
@Entity
@Table(name = "LECTURES")
public class Lecture {
    // Static Type members
    public static final String TYPE_VO = "VO";
    public static final String TYPE_UE = "UE";
    public static final String TYPE_VU = "VU";
    public static final String TYPE_SE = "SE";
    public static final String TYPE_PR = "PR";
    // Attributes
    @Id
    @GeneratedValue(generator = "lecture-id")
    @GenericGenerator(name = "lecture-id", strategy = "increment")
    private Long id;
    @Column(name = "LVA_NR")
    private String lectureNr;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @ManyToOne
    @JoinColumn(name = "prof_fk")
    private Professor professor;

    public Long getId() {
        return id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLectureNr() {
        return lectureNr;
    }

    public void setLectureNr(String lectureNr) {
        this.lectureNr = lectureNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
