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
    
    private boolean isTutor;

    public Student() {
    }

    public Student(String firstname, String lastname, String email, String matnr) {
    	super(firstname, lastname, email);
    	this.matnr = matnr;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Object stud = null;
		stud = super.clone();
		return stud;
	}

	public void setTutor(boolean isTutor) {
		this.isTutor = isTutor;
	}

	public boolean isTutor() {
		return isTutor;
	}

}
