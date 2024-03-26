import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id runtime-exec-analysis
 * @kind problem
 */

from MethodAccess call
where
    (call.getMethod().getName() = "exec" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.lang.Runtime") or 
    (call.getMethod().getName() = "start" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.lang.ProcessBuilder")
select call, "Runtime exec found"