import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id objectinputstream-readObject-analysis
 * @kind problem
 * 
 */

from MethodAccess call
where
    (call.getMethod().getName() = "readObject" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "java.io.ObjectInputStream")
select call, "ObjectInputStream readObject found"