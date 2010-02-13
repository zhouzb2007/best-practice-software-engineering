/**
 * 
 */
package at.ac.tuwien.ifs.bpse.services;

import java.util.List;

import at.ac.tuwien.ifs.bpse.domain.Student;

/**
 * The exporter interface contains service methods to export business data to different kind of
 * data.
 * @author SE Team
 */
public interface IExporter {
    /**
     * Export the given students
     * @param students that should be exported
     * @throws exception when an error occurs
     */
    public void exportStudents(List<Student> students)throws Exception;
    
}
