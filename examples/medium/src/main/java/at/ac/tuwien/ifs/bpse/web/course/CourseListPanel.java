package at.ac.tuwien.ifs.bpse.web.course;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.services.ICourseService;
import at.ac.tuwien.ifs.bpse.web.components.AbstractSimpleFilterPanel;
import at.ac.tuwien.ifs.bpse.web.student.StudentPage;

/**
 * Course List Panel. Provides an overview of Courses
 * 
 *  The component is used for
 *  - Administration (Add, Edit and Remove Courses)
 *  - Student can search and select a Course from the list
 * @author mde
 *
 */
public class CourseListPanel extends Panel {
	/* List of courses */
	private List<Course> courses;
	/* List View that displays the courses*/
	private PropertyListView<Course> list;
	//Injected Services
	@SpringBean
	private ICourseService courseService;
	/* Course list Mode */
	private CourseListMode listMode;
	
	/**
	 * Default Constructor wit an id
	 */
	public CourseListPanel(String id){
		this(id, null, CourseListMode.DEFAULT);
	}
	
	/**
	 * Constructor for a student course list
	 * @param _courses
	 */
	public CourseListPanel(String id,Student student, CourseListMode listMode){
		this(id, null, student, listMode);
	}

	/**
	 * Constructor for a course list in a Student context 
	 * @param _courses courses of the student
	 * @param student student
	 */
	public CourseListPanel(String id, List<Course> _courses, final Student student, final CourseListMode listMode){
		super(id);
		courses = _courses;
		//Markup Container in order to update the list via AJAX
		final WebMarkupContainer listContainer = new WebMarkupContainer("courseListContainer");
		listContainer.setOutputMarkupId(true);
		
		//Property List that contains the courses
		list= new PropertyListView<Course>("courseList", courses){
			@Override
			protected void populateItem(final ListItem<Course> item) {
				//Only show for students
				item.add(new AjaxLink("register"){
					@Override
					public void onClick(AjaxRequestTarget target) {
						courseService.registerToCourse(student, item.getModelObject());
						setResponsePage(new CourseListPage(student, courses, listMode));
					}
					@Override
					public boolean isVisible() {
						if(listMode.equals(CourseListMode.REGISTRATION)){
							return true;
						}else{
							return false;
						}
					}
				});
				//Only show for students
				item.add(new AjaxLink("unregister"){
					@Override
					public void onClick(AjaxRequestTarget target) {
						courseService.unregisterFromCourse(student, item.getModelObject());
						setResponsePage(new StudentPage(student));
					}

					@Override
					public boolean isVisible() {
						if(listMode.equals(CourseListMode.UNREGISTRATION)){
							return true;
						}else{
							return false;
						}
					}
					
				});
				item.add(new AjaxLink("details"){
					@Override
					public void onClick(AjaxRequestTarget target) {
						//Show details
						setResponsePage(new CoursePage(item.getModelObject()));
					}
					@Override
					public boolean isVisible() {
						if(listMode.equals(CourseListMode.DEFAULT)){
							return true;
						}else{
							return false;
						}
					}
				});
				item.add(new Label("title"));
				item.add(new Label("ects"));
			}
		};
		list.setOutputMarkupId(true);
		listContainer.add(list);
		add(listContainer);
		
		AbstractSimpleFilterPanel filterPanel = new AbstractSimpleFilterPanel("courseFilter"){
			@Override
			public void doRefresh(String filter) {
				if(student == null){
					courses = courseService.getCoursesByName(filter);
				}else{
					courses = courseService.getCoursesForStudent(filter, student);
				}
				list.setModelObject(courses);
			}
		};
		add(filterPanel);
		
		add(new AjaxLink<String>("newCourse"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(new CoursePage(new Course()));
			}
		});

	}
}
