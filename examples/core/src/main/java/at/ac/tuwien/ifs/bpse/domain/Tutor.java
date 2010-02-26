package at.ac.tuwien.ifs.bpse.domain;

public class Tutor extends Person {

	private String matnr;

	public Tutor(String matnr, String firstname, String lastname) {
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
	
}
