package at.ac.tuwien.ifs.qse.client;

import junit.framework.TestCase;

import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

import at.ac.tuwien.ifs.qse.ws.ISimpleService;

/**
 * Simple test cases that demonstrates how to call web services
 * @author Markus Demolsky
 *
 */
public class TestWebService extends TestCase {
    private String webServiceURL = "http://localhost:8080/seadvanced/services/simpleService";
    
    public void testCallWithProxy(){
        ISimpleService myService;
        //Create metamodel for service
        Service serviceModel = new ObjectServiceFactory().create(ISimpleService.class);
        try{
            //Receive service
            ISimpleService service = (ISimpleService)
            new XFireProxyFactory().create(serviceModel, webServiceURL);
            assertNotNull(service);
            //Invoke service
            assertEquals(service.getServerStatus(),"Server is running");
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }

}
