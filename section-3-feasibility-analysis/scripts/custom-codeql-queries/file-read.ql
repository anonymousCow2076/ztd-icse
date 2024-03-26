import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id file-read-analysis
 * @kind problem
 */

from ClassInstanceExpr instances
where
    instances.getType().getTypeDescriptor() = "Ljava/io/FileInputStream;" or
    instances.getType().getTypeDescriptor() = "Ljava/io/FileReader;"
select instances, "File read found"