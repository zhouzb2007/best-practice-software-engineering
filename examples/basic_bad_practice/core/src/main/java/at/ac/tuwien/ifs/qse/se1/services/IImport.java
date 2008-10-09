/**
 * 
 */
package at.ac.tuwien.ifs.qse.se1.services;

import java.util.List;
import at.ac.tuwien.ifs.qse.se1.bo.Professor;
import at.ac.tuwien.ifs.qse.se1.bo.Student;

/**
 * The IImport interface provides service methods to import business objects from different sources,
 * such as a CSV file or an XML source. All imported values are converted to a list of business objects
 * @author Demolsky Markus
 */
public interface IImport {
    
    /**
     * Import a list of students from a given source
     * @param source that contains the students. Can be a CSV file or a XML file
     * @return converted list of objects
     */
    public List<Student> importStudents(String source);
    
    /**
     * Import a list of professors from a given source
     * @param source that contains the professors. Can be a CSV file or a XML file
     * @return converted list of objects
     */
    public List<Professor> importProfessors(String source);
    
}
