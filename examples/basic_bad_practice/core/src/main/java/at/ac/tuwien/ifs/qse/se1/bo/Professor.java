package at.ac.tuwien.ifs.qse.se1.bo;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * POJO (Plain Old Java Object) Representing the internal state of a Professor. This Class
 * encapsulates all Datafields and Methods needed for a Professor.
 * @author The SE-Team
 * @version 1.0
 */
@Entity
@Table(name = "PROFESSOR")
public class Professor {
    @Id
    @Column(name = "PERSNR")
    private String persNr;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @OneToMany(mappedBy="professor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Lecture> lectures;

    public Set<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(Set<Lecture> lectures) {
        this.lectures = lectures;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersNr() {
        return persNr;
    }

    public void setPersNr(String persNr) {
        this.persNr = persNr;
    }
}