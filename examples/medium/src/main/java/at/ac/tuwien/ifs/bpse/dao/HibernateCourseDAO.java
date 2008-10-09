package at.ac.tuwien.ifs.bpse.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Exam;

public class HibernateCourseDAO extends HibernateDaoSupport implements ICourseDAO {
    // Declarations
    private static Logger log = Logger.getLogger("qse.se1.dao");

    public boolean deleteCourse(Course course) {
        try {
            getHibernateTemplate().delete(course);
            log.info("Delete course with id: " + course.getId());
            return true;
        } catch (DataAccessException e) {
            log.error("Error deleteCourse", e);
            return false;
        }
    }

    public Course getCourse(int id) {
        return (Course)getHibernateTemplate().get(Course.class, id);
    }

    public List<Course> getCourseByTitle(final String title) {
        List<Course> results = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(Course.class).add(Restrictions.ilike("title", title, MatchMode.START)).list();
            }
        });
        return results;
    }


    public List<Exam> getExamsOfCourse(final Course course) {
        List<Exam> results = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return session.createCriteria(Exam.class).add(Restrictions.eq("course", course)).list();
            }
        });
        return results;
    }
    
    public Course saveCourse(Course course) {
        return null;
    }

    public Course updateCourse(Course course) {
        return null;
    }


}
