package at.ac.tuwien.ifs.qse.se1.services;

import at.ac.tuwien.ifs.qse.se1.bo.Student;

/**
 * Service that can be accessed by students
 * @author Markus Demolsky
 *
 */
public interface IStudentService {
    
    /**
     * Retrieves one single Student by a given matrikel number
     * @param matnr
     * @return Student object if someone existed, otherwise null if no student with the given matnr
     *         exist
     */
    public Student getStudentByMatrNr(String matrNr);
}
