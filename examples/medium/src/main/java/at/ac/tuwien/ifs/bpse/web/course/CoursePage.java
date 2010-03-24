package at.ac.tuwien.ifs.bpse.web.course;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.services.ICourseService;
/**
 * Course Detail Page. This page can be used to show Account information
 * for a Course that can be updated by the Course.
 * @author mde
 *
 */
public class CoursePage extends WebPage {

	@SpringBean
	private ICourseService courseService;
	
	public CoursePage(final Course Course){
		add(new CourseForm("CourseForm", new CompoundPropertyModel<Course>(Course)));
	}
	
	private class CourseForm extends Form<Course>{

		public CourseForm(String id, final IModel<Course> model) {
			super(id, model);
			add(new TextField<Course>("title"));
			add(new TextField<Course>("ects"));
			add(new AjaxLink("remove"){
				@Override
				public void onClick(AjaxRequestTarget target) {
					courseService.removeCourse(model.getObject());
				}
			});
		}

		@Override
		protected void onSubmit() {
			//Update Course Account
			Course course = getModelObject();
			courseService.saveCourse(course);
		}
	}
}
