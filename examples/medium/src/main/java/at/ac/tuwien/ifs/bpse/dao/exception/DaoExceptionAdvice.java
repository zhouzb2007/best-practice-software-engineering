package at.ac.tuwien.ifs.bpse.dao.exception;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

/**
 * If a component throws an exception this advice can handle the exception. In order to
 * use this advice it must be define in the spring configuration as an Advice. Then it can 
 * be add to a Proxy as an interceptor.
 * @author SE Team
 */
public class DaoExceptionAdvice implements ThrowsAdvice {
    private final Logger log = Logger.getLogger(getClass());
    
    public void afterThrowing(Method m, Object[] args, Object target, Exception e)throws Exception{
        log.error("Exception in a DAO: " + e);
        log.error("Object: " + target);
        log.error("Method: " + m);
    }
}
