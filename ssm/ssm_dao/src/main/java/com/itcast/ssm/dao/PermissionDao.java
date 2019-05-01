package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 资源权限持久层接口
 */
public interface PermissionDao {
    /**
     * 通过角色id 查询出所对应的权限信息
     *
     * @param id
     * @return
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findPermissionByRoleId(String id) throws Exception;

    /**
     * 查询所有资源权限
     *
     * @return
     */
    @Select("select * from permission")
    List<Permission> findAll()throws Exception;

    /**
     * 添加资源权限
     *
     * @param permission
     */

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission)throws Exception;

    /**
     * 通过权限id 查询资源权限详情
     *
     * @param permissionId
     * @return
     */
    @Select("select * from permission where id=#{permissionId}")
    @Results(value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itcast.ssm.dao.RoleDao.findRoleByPermissionId"))
    })
    Permission findById(String permissionId)throws Exception;

    /**
     * 删除资源权限
     * @param permissionId
     */
    @Delete("delete from permission where id=#{permissionId}")
    void deletePermission(String permissionId)throws Exception;

    /**
     * 通过权限id移除该权限与其他表的关联
     * @param permissionId
     */
    @Delete("delete from role_permission where permissionId=#{permissionId}")
    void deleteRole_PermissionByPermissionId(String permissionId);
}
