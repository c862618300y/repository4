package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.PermissionDao;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源权限业务层接口实现类
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询所有资源权限
     *
     * @return
     */
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    /**
     * 添加资源权限
     *
     * @param permission
     */
    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }

    /**
     * 通过权限id 查询资源权限详情
     * @param permissionId
     * @return
     */
    @Override
    public Permission findById(String permissionId) throws Exception {
        return permissionDao.findById(permissionId);
    }

    /**
     * 通过权限id删除资源权限以及移除该权限与其他表的关联
     * @param permissionId
     */
    @Override
    public void deletePermission(String permissionId) throws Exception {
        //移除该权限与角色表之间的关联关系
        permissionDao.deleteRole_PermissionByPermissionId(permissionId);
        permissionDao.deletePermission(permissionId);
    }
}
