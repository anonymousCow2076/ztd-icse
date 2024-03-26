import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id async-file-analysis
 * @kind problem
 */

from MethodAccess call
where
    (call.getMethod().getName() = "open" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.nio.channels.AsynchronousFileChannel")
select call, "Async File found"