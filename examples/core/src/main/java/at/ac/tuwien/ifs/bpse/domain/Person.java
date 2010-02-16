package at.ac.tuwien.ifs.bpse.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
/**
 * This class holds the main data of one person, might be superclass for more specific roles 
 */
public class Person {

    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFullname(){
        return getFirstname() + " " + getLastname();
    }

}
