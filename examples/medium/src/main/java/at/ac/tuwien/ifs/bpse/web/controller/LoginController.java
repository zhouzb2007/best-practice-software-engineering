/**
 * 
 */
package at.ac.tuwien.ifs.bpse.web.controller;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import at.ac.tuwien.ifs.bpse.dao.IStudentDAO;
import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * A simple controller checking the whether the login student matrikel number exists or not. The
 * controller is used for dynamic navigation in the JSF application. Therefore it is necessary to
 * define the controller as a managed bean in the faces-config.xml
 * @author SE Team
 */
public class LoginController {
    // Declarations
    private static Logger log = Logger.getLogger("qse.se1");

    private IStudentDAO studentDao;
    private String matNr = "";

    /**
     * Dependency Injections
     */
    public void setStudentDao(IStudentDAO dao) {
        this.studentDao = dao;
    }

    public void setMatNr(String value) {
        this.matNr = value;
    }

    public String getMatNr() {
        return matNr;
    }

    /**
     * This method is called by the commandButton (action) on the studentLogin page. The method uses
     * the studentDao to verify the input. The studentDao is injected by the container.
     * @return success if the matrikel number exists, otherwise failure
     */
    public String verifyStudent() {
        log.info("Verifiy Student with inputed Matrikel number: " + matNr);
        Student loginStudent = studentDao.getStudentByMatrNr(matNr);
        if (loginStudent != null) {
            log.info("Student was verified: " + loginStudent.getFirstname() + " "
                    + loginStudent.getLastname());
            // Uses the faces context to update the managed bean with the loaded student
            FacesContext context = FacesContext.getCurrentInstance();
            // The managed bean 'student' will be replaced with the loaded student from the DAO. The
            // scope of the bean is the same as defined in the faces-config.xml
            context.getApplication().createValueBinding("#{sessionStudent}").setValue(context,
                    loginStudent);
            return "success";
        } else {
            log.warn("Invalid student tries to login");
            return "failure";
        }
    }
}