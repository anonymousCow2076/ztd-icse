import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id system-load-analysis
 * @kind problem
 */

from MethodAccess call
where
    (call.getMethod().getName() = "load" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.lang.System") or 
    (call.getMethod().getName() = "loadLibrary" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.lang.System")
select call, "System load found"