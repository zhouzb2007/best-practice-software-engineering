package at.ac.tuwien.ifs.bpse.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.domain.StudentExam;

/**
 * Convenient super class for Hibernate data access objects. Requires a SessionFactory to be set
 * (usually done by DI), providing a HibernateTemplate based on it to subclasses. The
 * HibernateTemplate can be accesses by using getHibernateTemplate(). The HibernateTemplate
 * implements the Template-Pattern. It simplifies Hibernate data access code, and converts checked
 * HibernateExceptions into unchecked DataAccessExceptions, following the org.springframework.dao
 * exception hierarchy.
 * @author SE1-Team
 */
public class HibernateStudentDAO extends HibernateDaoSupport implements IStudentDAO {
    // Declarations
    private static Logger log = Logger.getLogger("qse.se1.dao");

    public Student getStudent(int id) {
        try {
            return (Student) getHibernateTemplate().get(Student.class, id);
        } catch (Exception e) {
            log.error("Error getStudent", e);
            return null;
        }
    }

    public Student getStudentByMatrNr(String matnr) {
        try {
            Collection col = getHibernateTemplate().find("from Student where matnr=?", matnr);
            if (col.size() > 0) {
                return (Student) col.iterator().next();
            } else {
                log.warn("No student found with matnr " + matnr);
                return null;
            }
        } catch (Exception e) {
            log.error("Exception getStudentByMatrNr", e);
            return null;
        }
    }

    public Student saveStudent(Student student) {
        getHibernateTemplate().saveOrUpdate(student);
        log.info("Save student " + student.getFirstname() + " " + student.getLastname());
        return student;
    }

    public Student updateStudent(Student student) {
        getHibernateTemplate().update(student);
        log.info("Update student " + student.getFirstname() + " " + student.getLastname());
        return student;
    }

    public List<Student> getStudents(final String order) {
        List<Student> results = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria c = session.createCriteria(Student.class);
                c.addOrder(Order.asc(order));
                return c.list();
            }
        });
        return results;
    }

    public boolean deleteStudent(int id) {
        log.info("Delete student with id: " + id);
        try{
            getHibernateTemplate().delete(getStudent(id));
        }catch(DataAccessException e){
            return false;
        }
        return true;
    }

    public List<StudentExam> getRegisteredExams(final Student student) {
        List<StudentExam> results = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(StudentExam.class).add(Restrictions.eq("student", student)).list();
            }
        });
        return results;
    }
}
