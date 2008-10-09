/**
 * 
 */
package at.ac.tuwien.ifs.bpse.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import at.ac.tuwien.ifs.bpse.domain.Exam;
import at.ac.tuwien.ifs.bpse.domain.StudentExam;

/**
 * Convenient super class for Hibernate data access objects. Requires a SessionFactory to be set
 * (usually done by DI), providing a HibernateTemplate based on it to subclasses. The
 * HibernateTemplate can be accesses by using getHibernateTemplate(). The HibernateTemplate
 * implements the Template-Pattern. It simplifies Hibernate data access code, and converts checked
 * HibernateExceptions into unchecked DataAccessExceptions, following the org.springframework.dao
 * exception hierarchy.
 * @author SE Team
 */
public class HibernateExamDAO extends HibernateDaoSupport implements IExamDAO {
    // Declarations
    private static Logger log = Logger.getLogger("qse.se1.dao");

    /*
     * (non-Javadoc)
     * @see at.ac.tuwien.ifs.bpse.dao.IExamDAO#getExam(long)
     */
    public Exam getExam(long id) {
        try {
            return (Exam) getHibernateTemplate().get(Exam.class, id);
        } catch (DataAccessException e) {
            log.error("Error getExam", e);
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see at.ac.tuwien.ifs.bpse.dao.IExamDAO#saveExam(at.ac.tuwien.ifs.bpse.bo.Exam)
     */
    public Exam saveExam(Exam exam) {
        getHibernateTemplate().save(exam);
        return exam;
    }

    /*
     * (non-Javadoc)
     * @see at.ac.tuwien.ifs.bpse.dao.IExamDAO#updateExam(at.ac.tuwien.ifs.bpse.bo.Exam)
     */
    public Exam updateExam(Exam exam) {
        getHibernateTemplate().update(exam);
        return exam;
    }

    /*
     * (non-Javadoc)
     * @see at.ac.tuwien.ifs.bpse.dao.IExamDAO#deleteExam(long)
     */
    public boolean deleteExam(long id) {
        try {
            getHibernateTemplate().delete(getHibernateTemplate().load(Exam.class, id));
            log.info("Delete exam with id " + id);
            return true;
        } catch (Exception e) {
            log.error("Error saveProfessor", e);
            return false;
        }
    }

    public boolean deleteExam(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    public Exam getExam(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Exam> getExams() {
        return getHibernateTemplate().loadAll(Exam.class);
    }

    public StudentExam saveStudentExam(StudentExam studentExam) {
        getHibernateTemplate().save(studentExam);
        return studentExam;
    }

    
    
}
