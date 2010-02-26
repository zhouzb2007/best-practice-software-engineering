package at.ac.tuwien.ifs.bpse;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
/**
 * Todo description
 * @author SE Team
 *
 */
public class BaseTestCaseMedium extends AbstractDependencyInjectionSpringContextTests {
    @Override
    protected String[] getConfigLocations() {
//        return new String[] { "se-database-context.xml","se-medium-context.xml" };
        return new String[] { "" };
    }
    
    public void testInit(){
        
    }
}
