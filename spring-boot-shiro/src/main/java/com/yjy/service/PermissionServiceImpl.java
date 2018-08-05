package com.yjy.service;

import com.yjy.domain.Permission;
import com.yjy.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> getPermissions(Integer userId) {

            return permissionRepository.findPermissions(userId);

    }
}
