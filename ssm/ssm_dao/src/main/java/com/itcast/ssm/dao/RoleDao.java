package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色持久层接口
 */
public interface RoleDao {
    /**
     * 通过用户的id，查询出所有对应的角色信息
     * @param userId
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    public List<Role> findByUserId(String userId)throws Exception;

    /**
     * 通过用户id查询角色，再查询出角色所对应的权限
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(id = "roleMap",value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select = "com.itcast.ssm.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    /**
     * 查询所有角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAll()throws Exception;

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    /**
     * 通过角色id查询 查询角色详情
     * @param roleId
     * @return
     */
    @Select("select * from role where id=#{roleId}")
    @ResultMap("roleMap")
    Role findById(String roleId)throws Exception;

    /**
     * 通过资源权限id 查询出其所属的角色
     * @param permissionId
     * @return
     */
    @Select("select * from role where id in(select roleId from role_permission where permissionId=#{permissionId})")
    public List<Role> findRoleByPermissionId(String permissionId)throws Exception;

    /**
     * 通过角色id移除该角色和用户表之间的关联关系
     * @param roleId
     */
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId)throws Exception;

    /**
     * 通过角色id移除该角色和权限表之间的关联关系
     * @param roleId
     */
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId)throws Exception;

    /**
     *通过角色id,删除角色
     * @param roleId
     */
    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(String roleId)throws Exception;

    /**
     * 根据角色id 查询出角色和可以添加的权限
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByIdAndAllPermission(String roleId);

    /**
     * 给角色添权限
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
