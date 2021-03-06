package pl.javastart.util;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.transaction.UserTransaction;

@Interceptor
@TransactionalMethod
public class TransactionWrapper {
    
    @Inject
    private UserTransaction utx;
    
    @AroundInvoke
    public Object aroundInvoke(InvocationContext ic) throws Exception {
        utx.begin();
        try {
            return ic.proceed();
        } finally {
            utx.commit();
        }
    }
}