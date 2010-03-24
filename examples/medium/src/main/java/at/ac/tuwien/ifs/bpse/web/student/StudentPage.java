package at.ac.tuwien.ifs.bpse.web.student;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.services.ICourseService;
import at.ac.tuwien.ifs.bpse.services.IStudentService;
import at.ac.tuwien.ifs.bpse.web.BasePage;
import at.ac.tuwien.ifs.bpse.web.course.CourseListMode;
import at.ac.tuwien.ifs.bpse.web.course.CourseListPanel;
/**
 * Student Detail Page. This page can be used to show Account information
 * for a Student that can be updated by the student.
 * @author mde
 *
 */
public class StudentPage extends BasePage {

	@SpringBean
	private IStudentService studentService;
	@SpringBean
	private ICourseService courseService;
	
	
	
	public StudentPage(final Student student){
		super(student);
		boolean newRegistration = false;
		if(student.getId() == 0){
			newRegistration = true;
		}
		add(new StudentForm("studentForm", new CompoundPropertyModel<Student>(student)));
		//Button to reset password
		AjaxLink lnkReset = new AjaxLink("resetPassword"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				studentService.resetPassword(student);
			}
		};
		add(lnkReset);
		lnkReset.setVisible(!newRegistration);
		
		//List of courses
		CourseListPanel courseListPanel = new CourseListPanel("courseListPanel",
						courseService.getCoursesForStudent("", student), student, CourseListMode.UNREGISTRATION);
		add(courseListPanel);
		courseListPanel.setVisible(!newRegistration);
	}
	
	
	private class StudentForm extends Form<Student>{

		public StudentForm(String id, IModel<Student> model) {
			super(id, model);
			add(new TextField<Student>("matnr"));
			add(new TextField<Student>("firstname"));
			add(new TextField<Student>("lastname"));
			add(new TextField<Student>("email"));
			add(new CheckBox("tutor"));
		}

		@Override
		protected void onSubmit() {
			//Update Student Account
			Student s = getModelObject();
			studentService.updateAccount(getModelObject());
			setResponsePage(new BasePage(s));
		}
	}
}
