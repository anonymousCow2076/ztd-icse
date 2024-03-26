package com.anonuser.company;

import com.anonuser.company.enums.ResourceOp;
import com.anonuser.company.enums.ResourceType;

import java.util.Set;
import java.util.LinkedHashSet;

public interface PermissionsCallback {

    void onPermissionRequested(LinkedHashSet<String> subject, int subjectPathSize, ResourceType resourceType, ResourceOp resourceOp, String resourceItem);

    void onPermissionFailure(Set<String> subjectPaths, ResourceType resourceType, ResourceOp resourceOp, String resourceItem);

}
