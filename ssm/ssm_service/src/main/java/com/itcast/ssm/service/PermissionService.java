package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;

import java.util.List;

/**
 * 资源权限业务层接口
 */
public interface PermissionService {
    /**
     * 查询所有资源权限
     * @return
     */
    List<Permission> findAll() throws Exception;

    /**
     * 添加资源权限
     * @param permission
     * @param permission
     */
    void save(Permission permission) throws Exception;

    /**
     * 通过权限id 查询资源权限详情
     * @param permissionId
     * @return
     */
    Permission findById(String permissionId) throws Exception;

    /**
     * 删除资源权限
     * @param permissionId
     */
    void deletePermission(String permissionId) throws Exception;
}
