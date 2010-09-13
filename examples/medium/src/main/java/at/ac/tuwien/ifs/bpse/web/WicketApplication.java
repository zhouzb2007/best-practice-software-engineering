package at.ac.tuwien.ifs.bpse.web;

import org.apache.wicket.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.web.student.StudentPage;

/**
 * Application object for your web application. If you want to run this application without deploying, run the StartWicketApplication class.
 * 
 * @see at.ac.tuwien.ifs.bpse.medium.test.web.StartWicketApplication#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication
{    
	boolean isInitialized = false;
	
    /**
     * Constructor
     */
	public WicketApplication()
	{
	}
	
	@Override
    protected void init() {
        if (!isInitialized) {
            super.init();
            setListeners();
            isInitialized = true;
        }
    }

    private void setListeners() {
        addComponentInstantiationListener(new SpringComponentInjector(this));
    }
	
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<BasePage> getHomePage()
	{
		return BasePage.class;
	}

	@Override
	protected Class<WebSession> getWebSessionClass() {
		return WebSession.class;
	}

	@Override
	protected Class<LoginPage> getSignInPageClass() {
		return LoginPage.class;
	}

}
