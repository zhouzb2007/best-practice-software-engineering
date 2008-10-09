/**
 * 
 */
package at.ac.tuwien.ifs.qse.se1.services;

import java.util.List;
import at.ac.tuwien.ifs.qse.se1.bo.Student;

/**
 * The exporter interface contains service methods to export business data to different kind of
 * data.
 * @author Demolsky Markus
 */
public interface IExporter {
    /**
     * Export the given students
     * @param students that should be exported
     * @throws exception when an error occurs
     */
    public void exportStudents(List<Student> students)throws Exception;
    
}
