package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;

import java.util.List;

/**
 * 角色业务层接口
 */
public interface RoleService {
    /**
     * 查询所有角色
     * @return
     */
    public List<Role> findAll() throws Exception;

    /**
     * 添加角色
     * @param role
     */
    void save(Role role) throws Exception;

    /**
     * 通过角色id查询 查询角色详情
     * @param roleId
     * @return
     */
    Role findById(String roleId) throws Exception;

    /**
     *通过角色id删除角色以及移除角色与其它两张表的关联关系
     * @param roleId
     */
    void deleteRoleById(String roleId) throws Exception;

    /**
     * 根据角色id 查询出角色和可以添加的权限
     * @param roleId
     * @return
     */
    List<Permission> findRoleByIdAndAllPermission(String roleId);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}
