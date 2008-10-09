package at.ac.tuwien.ifs.qse.se1.dao;

import at.ac.tuwien.ifs.qse.se1.bo.Exam;

/**
 * The DAO abstracts and encapsulates all access to the data source corresponding to the Exam
 * business object
 * @author The SE-Team
 * @version 1.0
 */
public interface IExamDAO {
    /**
     * Retrieves one single Exam from the DB.
     * @param id unique database ID of Exam
     * @return Exam object holding the data or null if no exam with the matching id is found
     */
    public Exam getExam(long id);

    /**
     * Save one single Exam to the Database.
     * @param exam object holding the data of one exam
     * @return new exam object containing a unique id generated by database
     */
    public Exam saveExam(Exam exam);

    /**
     * Updates an existing exam in the database.
     * @param exam object holding the data of one exam
     * @return updated exam
     */
    public Exam updateExam(Exam exam);

    /**
     * Deletes an existing exam from the database.
     * @param id of the exam to be deleted
     * @return true if procedure was successfully, otherwise false
     */
    public boolean deleteExam(long id);
}
