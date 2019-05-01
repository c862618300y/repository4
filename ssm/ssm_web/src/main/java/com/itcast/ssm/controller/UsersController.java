package com.itcast.ssm.controller;

import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import com.itcast.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 用户界面层
 */
@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<UserInfo> userInfo = userService.findAll();
        mav.addObject("userList", userInfo);
        mav.setViewName("user-list");
        return mav;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    /**
     * 通过用户id 查询用户的详情
     *
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String uid) throws Exception {
        ModelAndView mav = new ModelAndView();
        UserInfo userInfo = userService.findById(uid);
        mav.addObject("user", userInfo);
        mav.setViewName("user-show");
        return mav;
    }

    /**
     * 根据用户id 查询出用户和可以添加的角色
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) String userId) throws Exception {
        ModelAndView mav = new ModelAndView();
        //根据用户id查询userInfo
        UserInfo userInfo = userService.findById(userId);
        mav.addObject("user", userInfo);
        //根据用户id查询出可以添加的角色
        List<Role> roles = userService.findByUserIdOtherRole(userId);
        mav.addObject("roleList", roles);
        //跳转到视图解析器所在的页面
        mav.setViewName("user-role-add");
        return mav;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) throws Exception {
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }
}
