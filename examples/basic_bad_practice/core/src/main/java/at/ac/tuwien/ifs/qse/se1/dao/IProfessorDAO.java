package at.ac.tuwien.ifs.qse.se1.dao;

import java.util.List;
import at.ac.tuwien.ifs.qse.se1.bo.Professor;

/**
 * The DAO abstracts and encapsulates all access to the data source
 * corresponding to the Professor business object
 * 
 * @author The SE-Team
 * @version 1.0
 */
public interface IProfessorDAO {
    /**
     * Retrieves one single Professor from the DB.
     * 
     * @param persNr unique database ID of Professor
     * @return Professor object holding the data of one professor or null if no
     *         professor with the matching id is found
     */
    public Professor getProfessor(String persNr);

    /**
     * Saves one single Professor to the Database.
     * 
     * @param professor object holding the data of one professor
     * @return new professor object containing a unique id generated by database
     */
    public Professor saveProfessor(Professor professor);

    /**
     * Updates an existing professor in the database.
     * 
     * @param professor object holding the data of one professor
     * @return updated professor
     */
    public Professor updateProfessor(Professor professor);

    /**
     * Deletes an existing professor from the database.
     * 
     * @param id of the professor to be deleted
     * @return unique id of the professor which was deleted or null if that id
     *         does not exist
     */
    public boolean deleteProfessor(String persNr);

    /**
     * Retrieves all professors from the database.
     * 
     * @param order database column which the result set should be ordered by
     * @return List of type Professor holding all stundents available
     */
    public List<Professor> getProfessors(String order);
    
        /**
         * Returns a <code>List</code> of <code>Professor</code>s that matches
         * the specified <code>String</code> <code>str</code>.
         * @param str search string.
         * @return <code>List</code> of <code>Professor</code>s.
         */
        public List<Professor>searchByName(String str);

}