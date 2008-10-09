package at.ac.tuwien.ifs.qse.ws;

import javax.jws.WebService;

/**
 * Demonstrates how to expose a service as a web service using Web Service
 * Annotations and XFire as a Web Service Framework.
 * 
 * @author Markus Demolsky
 * 
 */

@WebService
public interface ISimpleService {

    /**
     * Returns the status of the server
     * 
     * @return server status
     */
    public String getServerStatus();
}
