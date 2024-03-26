import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id file-write-analysis
 * @kind problem
 */

from ClassInstanceExpr instances
where
instances.getType().getTypeDescriptor() = "Ljava/io/FileOutputStream;" or
instances.getType().getTypeDescriptor() = "Ljava/io/FileWriter;"
select instances, "FileOutputStream found"