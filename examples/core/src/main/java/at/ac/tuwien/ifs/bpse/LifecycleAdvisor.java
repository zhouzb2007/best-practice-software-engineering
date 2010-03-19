/**
 * 
 */
package at.ac.tuwien.ifs.bpse;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import at.ac.tuwien.ifs.bpse.dao.interfaces.ICourseDAO;
import at.ac.tuwien.ifs.bpse.dao.interfaces.IExamDAO;
import at.ac.tuwien.ifs.bpse.dao.interfaces.IProfessorDAO;
import at.ac.tuwien.ifs.bpse.dao.interfaces.IStudentDAO;
import at.ac.tuwien.ifs.bpse.domain.Exam;
import at.ac.tuwien.ifs.bpse.domain.Lab;
import at.ac.tuwien.ifs.bpse.domain.Lecture;
import at.ac.tuwien.ifs.bpse.domain.Professor;
import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * The Lifecycle Advisor is responsible to setup an initial database context, by
 * using only the provided DAO interfaces. It provides a init-method for start
 * up and a destroy method to clean the whole context when the application
 * closes.
 * 
 * @author SE Team
 */
public class LifecycleAdvisor {
    // Declaration
    private static Logger log = Logger.getLogger("qse.se1");
    private IStudentDAO studentDao;
    private IProfessorDAO professorDao;
    private ICourseDAO courseDao;
    private IExamDAO examDao;

    /*
     * Dependency Injections
     */
    public void setStudentDao(IStudentDAO dao) {
        this.studentDao = dao;
    }

    public void setProfessorDao(IProfessorDAO dao) {
        this.professorDao = dao;
    }

    public void setCourseDao(ICourseDAO dao) {
        this.courseDao = dao;
    }

    public void setExamDao(IExamDAO dao) {
        this.examDao = dao;
    }

    /*
     * Init-Method which is called by startup. The invocation of this method
     * will be done by Spring
     */
    public void init() {
        log.info("Startup sample application for Software Engineering ......");
        log.info("Insert a couple of students to the database");
        insertStudents();
        log.info("Insert a couple of professors with lectures to the database");
        insertProfessorsAndCourses();
        log.info("Insert a couple of exams");
        insertExams();
    }
    
    public void initBasic(){
        System.out.println("Lifecycle advisor beim Basic wrid geladen");
        
    }

    private void insertStudents() {
        studentDao.saveStudent(new Student("0201857", "Max", "Superstudent"));
        studentDao.saveStudent(new Student("0201234", "Hans", "Huber"));
        studentDao.saveStudent(new Student("0325934", "Philipp", "Langer"));
        studentDao.saveStudent(new Student("0325059", "Simon", "Tragatschnig"));
        studentDao.saveStudent(new Student("0125826", "Petra", "Brosch"));
    }

    private void insertProfessorsAndCourses() {
        Professor p1 = new Professor("1212", "Profi", "Muster");
        p1.addCourse(new Lab("Software Engineering", 20, 30));
        p1.addCourse(new Lab("Software Engineering II", 20, 10));
        p1.addCourse(new Lecture("Design Principles", 20, false));
        p1.addCourse(new Lecture("Patterns & Idiome", 20, false));
        professorDao.saveProfessor(p1);

        Professor p2 = new Professor("8989", "Mathe", "Faxi");
        p2.addCourse(new Lab("Software Architecture in Practice", 20, 10));
        p2.addCourse(new Lecture("Event Driven Systems", 20, true));
        professorDao.saveProfessor(p2);

        professorDao.saveProfessor(new Professor("1365", "SVG", "Several"));
    }

    private void insertExams() {
        examDao.saveExam(new Exam(courseDao.getCourse(1), new GregorianCalendar(2007,
                Calendar.NOVEMBER, 12).getTime(), "FH HS 1"));
        examDao.saveExam(new Exam(courseDao.getCourse(2), new GregorianCalendar(2007,
                Calendar.OCTOBER, 22).getTime(), "FH HS 1"));
        examDao.saveExam(new Exam(courseDao.getCourse(1), new GregorianCalendar(2008,
                Calendar.JANUARY, 31).getTime(), "FH HS 5"));
        examDao.saveExam(new Exam(courseDao.getCourse(3), new GregorianCalendar(2007,
                Calendar.JANUARY, 31).getTime(), "HS 8"));

    }

}
