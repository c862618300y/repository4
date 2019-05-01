package com.itcast.ssm.service;

import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户业务层接口
 */
public interface UserService extends UserDetailsService {
    /**
     * 查询所有用户
     * @return
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 添加用户
     * @param userInfo
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 通过用户id 查询用户详情
     * @param id
     * @return
     */
    UserInfo findById(String id) throws Exception;

    /**
     * 根据用户id 查询出用户和可以添加的角色
     * @param userId
     * @return
     */
    List<Role> findByUserIdOtherRole(String userId);

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds);
}
