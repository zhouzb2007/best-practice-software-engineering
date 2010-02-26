/**
 * 
 */
package at.ac.tuwien.ifs.bpse.dao;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import at.ac.tuwien.ifs.bpse.dao.IProfessorDAO;
import at.ac.tuwien.ifs.bpse.domain.Professor;

/**
 * Convenient super class for Hibernate data access objects. Requires a
 * SessionFactory to be set (usually done by DI), providing a HibernateTemplate
 * based on it to subclasses. The HibernateTemplate can be accesses by using
 * getHibernateTemplate(). The HibernateTemplate implements the
 * Template-Pattern. It simplifies Hibernate data access code, and converts
 * checked HibernateExceptions into unchecked DataAccessExceptions, following
 * the org.springframework.dao exception hierarchy.
 * 
 * @author SE Team
 */
public class HibernateProfessorDAO extends HibernateDaoSupport implements
        IProfessorDAO {
    // Declarations
    private static Logger log = Logger.getLogger("qse.se1.dao");

    /*
     * (non-Javadoc)
     * 
     * @see at.ac.tuwien.ifs.bpse.dao.IProfessorDAO#getProfessor(long)
     */
    public Professor getProfessor(String persNr) {
        try {
            return (Professor) getHibernateTemplate().get(Professor.class,
                    persNr);
        } catch (DataAccessException e) {
            log.error("Error getProfessor", e);
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.ac.tuwien.ifs.bpse.dao.IProfessorDAO#saveProfessor(at.ac.tuwien.ifs.bpse.bo.Professor)
     */
    public Professor saveProfessor(Professor professor) {
        log.info("Save professor " + professor.getFirstname());
        getHibernateTemplate().saveOrUpdate(professor);
        return professor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.ac.tuwien.ifs.bpse.dao.IProfessorDAO#updateProfessor(at.ac.tuwien.ifs.bpse.bo.Professor)
     */
    public Professor updateProfessor(Professor professor) {
        log.info("Update professor " + professor.getFirstname());
        getHibernateTemplate().update(professor);
        return professor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.ac.tuwien.ifs.bpse.dao.IProfessorDAO#deleteProfessor(long)
     */
    public boolean deleteProfessor(String persNr) {
        try {
            getHibernateTemplate().delete(getProfessor(persNr));
            log.info("Delete professor with id: " + persNr);
            return true;
        } catch (DataAccessException e) {
            log.error("Error deleteProfessor", e);
            return false;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see at.ac.tuwien.ifs.bpse.dao.IProfessorDAO#getProfessors(java.lang.String)
     */
    public List<Professor> getProfessors(String order) {
        // TODO Auto-generated method stub
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Professor> getProfessorsByName(String name) {
        try {
            Collection col = getHibernateTemplate().find(
                    "FROM Professor WHERE UPPER(lastName) LIKE UPPER(?)", name);
            if (col.size() > 0) {
                return (List<Professor>) col;
            } else {
                log.warn("No professor with specified lastName LIKE " + name);
                return null;
            }
        } catch (Exception e) {
            log.error("Search professor by lastName failed", e);
            return null;
        }
    }

}
