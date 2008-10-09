package at.ac.tuwien.ifs.qse.se1.bo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * POJO (Plain Old Java Object) Representing the internal state of a Student. This Class
 * encapsulates all Datafields and Methods needed for a Student.
 * <p>
 * The POJO contains Hibernate annotations in order to persist the POJO in a database.
 * @author The SE-Team
 * @version 1.0
 */
@Entity
@Table(name = "STUDENT")
public class Student implements Serializable {
    @Id
    @GeneratedValue(generator = "student-id")
    @GenericGenerator(name = "student-id", strategy = "increment")
    private long id;
    @Column(name = "MATNR")
    private String matnr;
    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;
    @Column(name = "EMAIL")
    private String email;

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
