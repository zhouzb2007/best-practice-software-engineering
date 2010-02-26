/**
 * 
 */
package at.ac.tuwien.ifs.bpse.web.controller.search;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import at.ac.tuwien.ifs.bpse.dao.IProfessorDAO;

/**
 * Todo:
 * @author SE Team
 */
public class ProfessorSearchController extends AbstractSearchController {

    private IProfessorDAO professorDAO;

    public void setProfessorDao(IProfessorDAO dao) {
        this.professorDAO = dao;
    }

    /**
     * This method is invoked by the search button in
     * <code>professors.jsf</code>.
     */
    public void search() {
        setResult(professorDAO.getProfessorsByName(getSearchString() + "%"));
        if (getResult() == null || getResult().isEmpty()) {
            ResourceBundle bundle = ResourceBundle
                    .getBundle("messages", FacesContext.getCurrentInstance()
                            .getViewRoot().getLocale());
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, bundle
                            .getString("lblProfessorNoMatches"), bundle
                            .getString("lblProfessorNoMatches")));
        }
    }

}
