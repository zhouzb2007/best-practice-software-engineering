package at.ac.tuwien.ifs.bpse.medium.test.persistence;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( { CourseMapperTest.class, ExamMapperTest.class, ProfessorMapperTest.class, SqlMapStudentDaoTest.class, StudentMapperTest.class })
public class AllPersistanceTests {
}
