package at.ac.tuwien.ifs.bpse.web;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application. If you want to run this application without deploying, run the StartWicketApplication class.
 * 
 * @see at.ac.tuwien.ifs.bpse.web.StartWicketApplication#main(String[])
 */
public class WicketApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	public void init() {
        super.init();
        addComponentInstantiationListener(new SpringComponentInjector(this));
    }
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

}
