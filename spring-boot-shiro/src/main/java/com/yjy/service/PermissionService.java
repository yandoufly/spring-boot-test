package com.yjy.service;

import java.util.List;

import com.yjy.domain.Permission;

public interface PermissionService {
     List<Permission> getPermissions(Integer userId);


}
