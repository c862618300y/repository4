package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {
    /**
     * 通过用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    @Results(id = "userMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itcast.ssm.dao.RoleDao.findByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    /**
     * 查询所有用户
     *
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     *
     * @param userInfo
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * 通过用户id 查询用户详情
     *
     * @param id
     * @return
     */
    @Select("select * from users where id=#{id}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itcast.ssm.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id) throws Exception;

    /**
     * 根据用户id 查询出用户和可以添加的角色
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findByUserIdOtherRole(String userId);

    /**
     * 给用户添加角色
     *
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
