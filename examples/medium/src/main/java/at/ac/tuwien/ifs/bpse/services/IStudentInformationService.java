package at.ac.tuwien.ifs.bpse.services;

import java.util.List;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Exam;
import at.ac.tuwien.ifs.bpse.domain.Professor;
import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.domain.StudentExam;

public interface IStudentInformationService {
    
    public List<Course> searchCourseByTitle(String title);
    
    public Course getCourseById(int id);
    
    public List<Professor> searchProfessorByName(String name);
    
    public List<Exam> getExamsForCourse(Course course);
    
    public List<Exam> getAllExams();
    
    public List<StudentExam> getRegisteredExams(Student student);
    
    public boolean doExamRegistration(StudentExam exam);

}
