package at.ac.tuwien.ifs.bpse.web.controller;

import java.util.List;

import org.apache.log4j.Logger;

import at.ac.tuwien.ifs.bpse.domain.Course;
import at.ac.tuwien.ifs.bpse.domain.Exam;
import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.domain.StudentExam;
import at.ac.tuwien.ifs.bpse.services.IStudentInformationService;

public class ServiceController {
    private static Logger log = Logger.getLogger("qse.se1");
    private IStudentInformationService informationService;
    private boolean registerSuccess;

    public void setInformationService(IStudentInformationService service){
        this.informationService = service;
    }
    
    public String doExamRegistration(){
        log.info("do exam registration...");
        StudentExam studentExam = new StudentExam();
        studentExam.setExam((Exam)JsfBeanManager.getBean("exam"));
        studentExam.setStudent((Student)JsfBeanManager.getBean("sessionStudent"));
        registerSuccess = informationService.doExamRegistration(studentExam);
        if(registerSuccess){
            return "registerSuccess";
        }else{
            return "registerFailure";
        }
    }
    
    public List<Exam> getExamsForSelectedCourse(){
        Course course = (Course)JsfBeanManager.getBean("course");
        return informationService.getExamsForCourse(course);
    }
    
    public List<StudentExam>getRegisteredExams(){
        return informationService.getRegisteredExams((Student)JsfBeanManager.getBean("sessionStudent"));
    }

    public boolean isRegisterSuccess() {
        return registerSuccess;
    }
}
