import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id jndi-lookup-analysis
 * @kind problem
 */

from MethodAccess call
where
    (call.getMethod().getName() = "lookup" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "javax.naming.InitialContext")
select call, "Jndi Lookup found"