package at.ac.tuwien.ifs.bpse.domain;


/**
 * Professor extends Person, hence inherits main properties from Person.
 */
public class Professor extends Person {
    private String officenr;

    public Professor(){
        super();
    }
    
    public Professor(String firstname, String lastname, String email, String officenr){
        super(firstname, lastname, email);
        this.officenr = officenr;
    }

    public String getOfficenr() {
        return officenr;
    }

    public void setOfficenr(String officenr) {
        this.officenr = officenr;
    }

}
