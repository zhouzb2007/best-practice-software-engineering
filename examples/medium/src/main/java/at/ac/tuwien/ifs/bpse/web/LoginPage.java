package at.ac.tuwien.ifs.bpse.web;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
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
	@SpringBean
	private IStudentService studentService;

	public LoginPage(){
		add(new LoginForm("loginForm", new CompoundPropertyModel<Student>(new Student())));
		add(new AjaxLink("register"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new StudentPage(new Student()));
			}
		});
	}
	
	/**
	 * Login Form
	 */
	private class LoginForm extends Form<Student>{

		public LoginForm(String id, IModel<Student> model) {
			super(id, model);
			add(new TextField<Student>("username"));
			add(new PasswordTextField("password"));
		}

		@Override
		protected void onSubmit() {
			//Update Student Account
			Student s = getModelObject();
			s = studentService.login(s.getUsername(), s.getPassword());
			if(s != null){
				setResponsePage(new BasePage(s));
			}else{
				//TODO: Show validation error message, that username/password is not correct
			}
		}
	}

	
	

}
