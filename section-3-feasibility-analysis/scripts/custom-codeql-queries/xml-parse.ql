import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id documentbuilder-parse-analysis
 * @kind problem
 */

from MethodAccess call
where
    (call.getMethod().getName() = "parse" and 
    call.getMethod().getDeclaringType().getQualifiedName() = "javax.xml.parsers.DocumentBuilder")
select call, "DocumentBuilder parse found"