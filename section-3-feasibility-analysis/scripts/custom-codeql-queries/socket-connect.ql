import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id socket-connect-analysis
 * @kind problem
 */

from ClassInstanceExpr instances
where
    instances.getType().getTypeDescriptor() = "Ljava/net/Socket;"
select instances, "Socket Connect found"