package at.ac.tuwien.ifs.bpse.dao.interfaces;

import java.util.List;

import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * The DAO abstracts and encapsulates all access to the data source
 * corresponding to the Student business object
 * 
 * @author The SE-Team
 * @version 1.1
 */
public interface IStudentDAO {

	public enum SortOrder {
		matnr,
		lastname
	}

	/**
	 * Retrieves one single Student from the DB.
	 * 
	 * @param id
	 *            unique database ID of Student
	 * @return Student object holding the data of one student or null if no
	 *         student with the matching id is found
	 */
	public Student getStudent(int id);

	/**
	 * Retrieves one single Student by a given matrikel number
	 * 
	 * @param matnr
	 * @return Student object if someone existed, otherwise null if no student
	 *         with the given matnr exist
	 */
	public Student getStudentByMatrNr(String matnr);

	/**
	 * Saves one single Student to the Database.
	 * 
	 * @param student
	 *            object holding the data of one student
	 * @return Student object with the generated ID, or null if an error occurs
	 */
	public Student saveStudent(Student student);

	/**
	 * Updates an existing student in the database.
	 * 
	 * @param student
	 *            object holding the data of one student
	 * @return Student object
	 */
	public Student updateStudent(Student student);

	/**
	 * Deletes an existing student from the database.
	 * 
	 * @param id
	 *            of the student to be deleted
	 * @return true if procedure was successfully, otherwise false
	 */
	public boolean deleteStudent(int id);

	/**
	 * Retrieves all students from the database.
	 * 
	 * @param SortOrder
	 *            that the result set should be ordered by
	 * @return List of type Student holding all students available
	 * 
	 * @since 1.1
	 * @see SortOrder
	 */
	public List<Student> getStudents(SortOrder order);

}
