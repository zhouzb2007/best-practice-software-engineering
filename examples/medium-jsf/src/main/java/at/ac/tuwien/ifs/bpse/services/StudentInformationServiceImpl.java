package at.ac.tuwien.ifs.bpse.services;

import java.util.List;

import at.ac.tuwien.ifs.bpse.dao.ICourseDAO;
import at.ac.tuwien.ifs.bpse.dao.IExamDAO;
import at.ac.tuwien.ifs.bpse.dao.IProfessorDAO;
import at.ac.tuwien.ifs.bpse.dao.IStudentDAO;
import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Exam;
import at.ac.tuwien.ifs.bpse.domain.Professor;
import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.domain.StudentExam;

public class StudentInformationServiceImpl implements IStudentInformationService {

    private IExamDAO examDao;
    private ICourseDAO courseDao;
    private IProfessorDAO professorDao;
    private IStudentDAO studentDao;

    public void setProfessorDao(IProfessorDAO professorDao) {
        this.professorDao = professorDao;
    }

    public void setCourseDao(ICourseDAO courseDao) {
        this.courseDao = courseDao;
    }

    public void setExamDao(IExamDAO examDao) {
        this.examDao = examDao;
    }

    public void setStudentDao(IStudentDAO studentDao) {
        this.studentDao = studentDao;
    }

    public boolean doExamRegistration(StudentExam exam) {
        //Check if the student is already registered for the exam
        if(examDao.saveStudentExam(exam) == null)
            return false;
        return true;
       
    }

    public List<StudentExam> getRegisteredExams(Student student) {
        return studentDao.getRegisteredExams(student);
    }

    public List<Course> searchCourseByTitle(String title) {
        return courseDao.getCourseByTitle(title);
    }

    public List<Professor> searchProfessorByName(String name) {
        return professorDao.getProfessorsByName(name);
    }

    public List<Exam> getExamsForCourse(Course course) {
        return courseDao.getExamsOfCourse(course);
    }
    
    public List<Exam> getAllExams(){
        return examDao.getExams();
    }

    public Course getCourseById(int id) {
        return courseDao.getCourse(id);
    }

}
