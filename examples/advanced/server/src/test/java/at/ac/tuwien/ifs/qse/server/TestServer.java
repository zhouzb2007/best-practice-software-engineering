package at.ac.tuwien.ifs.qse.server;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;


public class TestServer extends AbstractDependencyInjectionSpringContextTests {
    // Declaration
    protected String[] getConfigLocations() {
        return null;
        //return new String[] { "at/ac/tuwien/ifs/qse/ctx/se-database-context.xml",
        //        "at/ac/tuwien/ifs/qse/ctx/se-medium-context.xml", "/ws-hessian-servlet.xml" };
    }

    public void testInitServer() {
        
    }
}
