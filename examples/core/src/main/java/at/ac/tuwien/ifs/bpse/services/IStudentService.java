package at.ac.tuwien.ifs.bpse.services;

import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * Service that can be accessed by students
 * @author SE Team
 *
 */
public interface IStudentService {
   
    /**
     * Retrieves one single Student from the DB. Should delegate the request to a DAO service
     * @param id unique database ID of Student
     * @return Student object holding the data of one student or null if no student with the
     *         matching id is found
     */
    public Student getStudent(long id);

    /**
     * Retrieves one single Student by a given matrikel number. Should delegate the request to a DAO service
     * @param matnr
     * @return Student object if someone existed, otherwise null if no student with the given matnr
     *         exist
     */
    public Student getStudentByMatrNr(String matnr);

    /**
     * Saves one single Student to the Database. Should delegate the request to a DAO service
     * @param student object holding the data of one student
     * @return Student object with the generated ID, or null if an error occurs
     */
    public Student saveStudent(Student student);

    /**
     * Updates an existing student in the database. Should delegate the request to a DAO service
     * @param student object holding the data of one student
     * @return Student object
     */
    public Student updateStudent(Student student);

    /**
     * Deletes an existing student from the database. Should delegate the request to a DAO service
     * @param id of the student to be deleted
     * @return true if procedure was successfully, otherwise false
     */
    public boolean deleteStudent(int id);
    
    
    
}
