package at.ac.tuwien.ifs.bpse.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class BaseDaoTestCaseMedium extends AbstractDependencyInjectionSpringContextTests {
    // Declaration
    private SessionFactory sessionFactory;
    private Session session;

    @Override
    protected String[] getConfigLocations() {
        return new String[] { "at/ac/tuwien/ifs/qse/ctx/se-database-context.xml","at/ac/tuwien/ifs/qse/ctx/se-medium-context.xml" };
    }
    public void setSessionFactory(SessionFactory localSessionFactory) {
        this.sessionFactory = localSessionFactory;
    }

    protected void onSetUp() throws Exception {
        // Hold session open during test
        session = SessionFactoryUtils.getSession(this.sessionFactory, true);
        TransactionSynchronizationManager.bindResource(this.sessionFactory, new SessionHolder(
                session));
    }

    protected void onTearDown() throws Exception {
        session.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        SessionFactoryUtils.releaseSession(session, sessionFactory);
    }
    
    public void testInit(){
    }
}
