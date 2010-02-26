/**
 * 
 */
package at.ac.tuwien.ifs.bpse.web.controller.search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import at.ac.tuwien.ifs.bpse.dao.ICourseDAO;
import at.ac.tuwien.ifs.bpse.domain.Course;

/**
 * Todo:
 * @author SE Team
 */
public class CourseSearchController extends AbstractSearchController {

    private ICourseDAO courseDAO;

    public void setCourseDao(ICourseDAO dao) {
        this.courseDAO = dao;
    }
    
    /**
     * This method is invoked by the search button in
     * <code>professors.jsf</code>.
     */
    public void search() {
        setResult(courseDAO.getCourseByTitle(getSearchString() + "%"));
        if (getResult() == null || getResult().isEmpty()) {
            ResourceBundle bundle = ResourceBundle
                    .getBundle("messages", FacesContext.getCurrentInstance()
                            .getViewRoot().getLocale());
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
                            .getString("lblCourseNoMatches"), bundle
                            .getString("lblCourseNoMatches")));
        }
    }
    
    public List<SelectItem> getAllCourses(){
        List<SelectItem> courses = new ArrayList<SelectItem>();
        Iterator<Course> i = courseDAO.getCourseByTitle("%").iterator(); 
        
        while(i.hasNext()){
            Course c = i.next();
            courses.add(new SelectItem(Integer.toString(c.getId()), c.getTitle()));
        }
        return courses;
    }

}
