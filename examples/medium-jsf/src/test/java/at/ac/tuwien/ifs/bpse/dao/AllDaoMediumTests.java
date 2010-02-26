package at.ac.tuwien.ifs.bpse.dao;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllDaoMediumTests {
    public static Test suite() {
        TestSuite suite = new TestSuite("Test for at.ac.tuwien.ifs.bpse");
        //$JUnit-BEGIN$
//        suite.addTestSuite(TestHibernateStudentDao.class);
//        suite.addTestSuite(TestHibernateExamDao.class);
        //$JUnit-END$
        return suite;
    }
}
