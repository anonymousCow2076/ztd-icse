import java

/**
 * Finds instances where a FileOutputStream object is created.
 * @id socket-server-analysis
 * @kind problem
 */

//  this.ss = ServerSocketFactory.getDefault().createServerSocket(this.port);
// this.ss.accept() where ss is of type ServerSocket
from ClassInstanceExpr instances
where
    instances.getType().getTypeDescriptor() = "Ljava/net/ServerSocket;"
select instances, "Socker Server found"