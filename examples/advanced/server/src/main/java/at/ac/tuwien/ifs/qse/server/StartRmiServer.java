/*
 * Copyright 2004-2006 the original author or authors.
 * 
 */
package at.ac.tuwien.ifs.qse.server;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Starter for the RMI Server. Before the client can access the services the server must be startup
 * @author Markus Demolsky
 */
public class StartRmiServer {
    private static Logger log = Logger.getLogger("at.ac.tuwien.ifs.qse.server.RMIServer");
    /**
     * @param args
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[] { "at/ac/tuwien/ifs/qse/server/ctx/se-server.xml"});
        log.info("RMI Server is running.....");
    }
}
