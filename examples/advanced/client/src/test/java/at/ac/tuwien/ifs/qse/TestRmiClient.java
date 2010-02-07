package at.ac.tuwien.ifs.qse;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;
import at.ac.tuwien.ifs.qse.se1.services.IStudentService;

public class TestRmiClient extends AbstractDependencyInjectionSpringContextTests {

    public IStudentService studentService;
    
    public void setStudentService(IStudentService service){
        studentService = service;   
    }
    
    
    @Override
    protected String[] getConfigLocations() {
        return new String[]{"at/ac/tuwien/ifs/qse/client/ctx/se-rmi-client-context.xml"};
    }
    
    public void testConnectStudentService(){
        assertNotNull(studentService);
        //Call RMI service
        studentService.getStudentByMatrNr("0201857");
    }
    
    
}
