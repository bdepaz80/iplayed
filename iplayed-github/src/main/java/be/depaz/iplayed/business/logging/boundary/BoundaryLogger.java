/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.depaz.iplayed.business.logging.boundary;

import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author bdepaz
 */
public class BoundaryLogger {
    
    private static Logger logger = Logger.getLogger(BoundaryLogger.class.getName());
    
    @AroundInvoke
    public Object logCall(InvocationContext ic) throws Exception {
        long start = System.currentTimeMillis();
        try {
            StringBuilder builder = new StringBuilder("Invoke: ");
            builder.append(ic.getClass().getName() + "." + ic.getMethod().getName());
            builder.append("(");
            boolean first = true;
            if (ic.getParameters() != null) 
                for (Object o : ic.getParameters()) {
                    if (!first)
                        builder.append(", ");
                    builder.append(o);
                    first = false;
                }
            builder.append(")");
            logger.finest(builder.toString());
            return ic.proceed();
        } finally {
            long duration = System.currentTimeMillis() - start;
            //nothing here for now.
        }
    }
    
}
