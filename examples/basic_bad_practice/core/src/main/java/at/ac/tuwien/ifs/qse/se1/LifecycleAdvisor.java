/**
 * 
 */
package at.ac.tuwien.ifs.qse.se1;

import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import at.ac.tuwien.ifs.qse.se1.bo.Lecture;
import at.ac.tuwien.ifs.qse.se1.bo.Professor;
import at.ac.tuwien.ifs.qse.se1.bo.Student;
import at.ac.tuwien.ifs.qse.se1.dao.IProfessorDAO;
import at.ac.tuwien.ifs.qse.se1.dao.IStudentDAO;

/**
 * The Lifecycle Advisor is responsible to setup an initial database context, by using only the
 * provided DAO interfaces. It provides a init-method for start up and a destroy method to clean the
 * whole context when the application closes.
 * @author Demolsky Markus
 */
public class LifecycleAdvisor {
    // Declaration
    private static Logger log = Logger.getLogger("qse.se1");
    private IStudentDAO studentDao;
    private IProfessorDAO professorDao;

    /*
     * Dependency Injections
     */
    public void setStudentDao(IStudentDAO dao) {
        this.studentDao = dao;
    }

    public void setProfessorDao(IProfessorDAO dao) {
        this.professorDao = dao;
    }

    /*
     * Init-Method which is called by startup. The invocation of this method will be done by Spring
     */
    public void init() {
        log.info("Startup sample application for Software Engineering 1......");
        log.info("Insert a couple of students to the database");
        insertStudents();
        log.info("Insert a couple of professors with lectures to the database");
        insertProfessors();
    }

    private void insertStudents() {
        Student s1 = new Student();
        s1.setMatnr("0201857");
        s1.setFirstName("Markus");
        s1.setLastName("Demolsky");
        s1.setEmail("mymail@provider.com");
        studentDao.saveStudent(s1);
        Student s2 = new Student();
        s2.setMatnr("0202064");
        s2.setFirstName("Walter");
        s2.setLastName("Pindhofer");
        s2.setEmail("walters@mail.com");
        studentDao.saveStudent(s2);
        Student s3 = new Student();
        s3.setMatnr("0325934");
        s3.setFirstName("Philip");
        s3.setLastName("Langer");
        s3.setEmail("langer@big.tuwien.ac.at");
        studentDao.saveStudent(s3);
        Student s4 = new Student();
        s4.setMatnr("0325059");
        s4.setFirstName("Simon");
        s4.setLastName("Tragatschnig");
        s4.setEmail("tragatschnig@big.tuwien.ac.at");
        studentDao.saveStudent(s4);
        Student s5 = new Student();
        s5.setMatnr("0125826");
        s5.setFirstName("Petra");
        s5.setLastName("Brosch");
        s5.setEmail("brsoch@big.tuwien.ac.at");
        studentDao.saveStudent(s5);
    }

    private void insertProfessors() {
        Professor p1 = new Professor();
        p1.setPersNr("2255");
        p1.setFirstName("Alexander");
        p1.setLastName("Schatten");
        // Lectures of professor
        Lecture l_1 = new Lecture();
        l_1.setLectureNr("188.152");
        l_1.setName("Software Engineering 1");
        l_1.setType(Lecture.TYPE_VU);
        l_1.setProfessor(p1);
        Lecture l_2 = new Lecture();
        l_2.setLectureNr("188.158");
        l_2.setName("Software Engineering 2");
        l_2.setType(Lecture.TYPE_PR);
        l_2.setProfessor(p1);
        Set<Lecture> lectures = new HashSet<Lecture>();
        lectures.add(l_1);
        lectures.add(l_2);
        p1.setLectures(lectures);
        professorDao.saveProfessor(p1);
        
        Professor p2 = new Professor();
        p2.setPersNr("6767");
        p2.setFirstName("Stefan");
        p2.setLastName("Biffl");
        // Lectures of professor
        Lecture l_3 = new Lecture();
        l_3.setLectureNr("188.154");
        l_3.setName("Fortgeschrittene Aspekete des Qualitätsmanagement");
        l_3.setType(Lecture.TYPE_VU);
        l_3.setProfessor(p2);
        Lecture l_4 = new Lecture();
        l_4.setLectureNr("188.158");
        l_4.setName("Software Engineering 2");
        l_4.setType(Lecture.TYPE_PR);
        l_4.setProfessor(p2);
        Set<Lecture> lectures2 = new HashSet<Lecture>();
        lectures2.add(l_3);
        lectures2.add(l_4);
        p2.setLectures(lectures2);
        professorDao.saveProfessor(p2);
        
        Professor p3 = new Professor();
        p3.setPersNr("1001");
        p3.setFirstName("Gerti");
        p3.setLastName("Kappel");
        // Lectures of professor
        Lecture l_5 = new Lecture();
        l_5.setLectureNr("188.394");
        l_5.setName("Entwicklung von Web-Anwendungen");
        l_5.setType(Lecture.TYPE_VU);
        l_5.setProfessor(p2);
        Lecture l_6 = new Lecture();
        l_6.setLectureNr("188.391");
        l_6.setName("Objektorientierte Modellierung");
        l_6.setType(Lecture.TYPE_VU);
        l_6.setProfessor(p3);
        Set<Lecture> lectures3 = new HashSet<Lecture>();
        lectures3.add(l_5);
        lectures3.add(l_6);
        p3.setLectures(lectures3);
        professorDao.saveProfessor(p3);
    }
}
