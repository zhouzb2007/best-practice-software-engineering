package at.ac.tuwien.ifs.bpse.domain;


/**
 * Student extends Person, hence inherits all properties from Person class (main data of a person)
 */
public class Student extends Person implements java.io.Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3011368781691657737L;
	/* Matrikel Number */
    private String matnr;
    /* Username for the web application */
    private String username;
    /* Password for the web application */
    private String password;
    /* Flag that informs if the student also acts as turor */
    private boolean tutor;

    public Student() {
    }

    public Student(String matnr, String firstname, String lastname) {
        setMatnr(matnr);
        setFirstname(firstname);
        setLastname(lastname);
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isTutor() {
		return tutor;
	}

	public void setTutor(boolean tutor) {
		this.tutor = tutor;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Object stud = null;
		stud = super.clone();
		return stud;
	}

}
