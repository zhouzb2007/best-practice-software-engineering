package at.ac.tuwien.ifs.bpse.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.services.IStudentService;
import at.ac.tuwien.ifs.bpse.web.student.StudentPage;

/**
 * Login Page for the BPSE Wicket Sample. The Login Page
 * can also be used to start a new registration
 * @author mde
 *
 */
public class LoginPage extends WebPage {
	
	/**
	 * Retrieves the logger for this class.
	 */
	private static Log log = LogFactory.getLog(LoginPage.class);
	
	@SpringBean
	private IStudentService studentService;

	public LoginPage(){
		add(new LoginForm("loginForm"));
		add(new AjaxLink("register"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				//TODO: create a Registration Page
				setResponsePage(new StudentPage(new Student()));
			}
		});
		add(new AjaxLink("reset"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				//TODO: implement
				//studentService.resetPassword(new Student());
			}
		});
	}
	
	/**
	 * Login Form
	 */
	private class LoginForm extends Form{

		private String username;

	    private String password;
		
		public LoginForm(String id) {
			super(id);
			setModel(new CompoundPropertyModel(this));
			add(new TextField<Student>("username"));
			add(new PasswordTextField("password"));
		}

		@Override
		protected void onSubmit() {
			AuthenticatedWebSession session = AuthenticatedWebSession.get();
	        if(session.signIn(username, password)) {
	            setDefaultResponsePageIfNecessary();
	            //setResponsePage(new BasePage(studentService.getStudentByUsername(username)));
	        } else {
	            error(getString("login.failed"));
	        }
		}
		
		private void setDefaultResponsePageIfNecessary() {
	        if(!continueToOriginalDestination()) {
	            setResponsePage(getApplication().getHomePage());
	        }
	    }
	}

	
	

}
