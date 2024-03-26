import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id jndi-bind-analysis
 * @kind problem
 */

from MethodAccess call
where
    (call.getMethod().getName() = "bind" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "javax.naming.InitialContext")
select call, "JNDI bind found"