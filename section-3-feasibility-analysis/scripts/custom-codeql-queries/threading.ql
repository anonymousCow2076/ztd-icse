import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id threading-analysis
 * @kind problem
 */

from MethodAccess call
where
    (call.getMethod().getName() = "start" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.lang.Thread") or 
    (call.getMethod().getName() = "runAsync" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.util.concurrent.CompletableFuture") or 
    (call.getMethod().getName() = "submit" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.util.concurrent.ExecutorService")
select call, "Threading found"