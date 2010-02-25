package at.ac.tuwien.ifs.bpse.domain;

import java.io.Serializable;

/**
 * Lab is extending Course; hence inherits all properties of Course.
 * 
 */
public class Lab extends Course implements Serializable{
    
	private static final long serialVersionUID = 789893929429547336L;
	private int plimitation;
    
    public Lab(){}
    
    public Lab(String title, int ecds, int plimitation){
        setTitle(title);
        setEcts(ecds);
        setPlimitation(plimitation);
    }

    public int getPlimitation() {
        return plimitation;
    }

    public void setPlimitation(int plimitation) {
        this.plimitation = plimitation;
    }

}
