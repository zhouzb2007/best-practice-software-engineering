package at.ac.tuwien.ifs.bpse.dao;

import java.util.List;

import at.ac.tuwien.ifs.bpse.dao.IStudentDAO;
import at.ac.tuwien.ifs.bpse.domain.Student;


public class TestHibernateStudentDAO extends BaseDaoTestCaseMedium {
    private IStudentDAO studentDao;
    private static int newStudentId = 0;
    
    public void setStudentDao(IStudentDAO dao){
        studentDao = dao;
    }
    
    public void testGetStudent() {
    }

    public void testGetStudentByMatrNr() {
        Student exp =new Student("0201857", "Max", "Superstudent");
        Student res = studentDao.getStudentByMatrNr("0201857");
        assertNotNull(res);
        assertEquals("0201857", res.getMatnr());
        assertEquals("Max", res.getFirstname());
        assertEquals("Superstudent", res.getLastname());
    }

    public void testSaveStudent() {
        Student exp =new Student("0201858", "Neuer", "Student");
        studentDao.saveStudent(exp);
        Student res = studentDao.getStudentByMatrNr("0201858");
        newStudentId = res.getId();
        assertNotNull(res);
        assertSame(exp, res);
    }

    public void testUpdateStudent() {
        Student exp = studentDao.getStudentByMatrNr("0201858");
        exp.setFirstname("Neuer Update");
        studentDao.updateStudent(exp);
        Student res = studentDao.getStudentByMatrNr("0201858");
        assertNotNull(res);
        assertSame(exp, res);
    }
    
    public void testGetRegisteredExams() {
        Student res = studentDao.getStudentByMatrNr("0201858");
        assertNotNull(res);
        List exams = studentDao.getRegisteredExams(res);
        assertNotNull(exams);
        assertEquals(0,exams.size());
    }

    public void testDeleteStudentLong() {
    }

    public void testGetStudentsOrderByMatrNr() {
        // Order by Matrikelnummer
        List<Student> students = studentDao.getStudents("matnr");
        assertNotNull(students);
        Student pstud = null;
        for (Student stud : students) {
                if (pstud != null) {
                        assertNotSame(pstud.getId(), stud.getId());
                        assertNotSame(pstud.getMatnr(), stud.getMatnr());
                        assertTrue(stud.getMatnr().compareTo(pstud.getMatnr()) > 0);
                }
                pstud = stud;
        }
    }

    public void testDeleteStudent() {
        studentDao.deleteStudent(newStudentId);
        Student res = studentDao.getStudentByMatrNr("0201858");
        assertNull(res);
    }


}
