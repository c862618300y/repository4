package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.RoleDao;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色业务层接口实现类
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询所有角色
     * @return
     */
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    /**
     * 通过角色id查询 查询角色详情
     * @param roleId
     * @return
     */
    @Override
    public Role findById(String roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    /**
     * 通过角色id删除角色以及移除角色与其它两张表的关联关系
     * @param roleId
     */
    @Override
    public void deleteRoleById(String roleId) throws Exception {
        //移除该角色和用户表之间的关联关系
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //移除该角色和权限表之间的关联关系
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //最后删除该角色
        roleDao.deleteRoleById(roleId);
    }

    /**
     * 根据角色id 查询出角色和可以添加的权限
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findRoleByIdAndAllPermission(String roleId) {
        return roleDao.findRoleByIdAndAllPermission(roleId);
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        //循环添加
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
