package com.itcast.ssm.controller;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 角色界面层
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mav.addObject("roleList", roleList);
        mav.setViewName("role-list");
        return mav;
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception {
        roleService.save(role);
        return "redirect:findAll.do";
    }

    /**
     * 通过角色id查询 查询角色详情
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mav = new ModelAndView();
        Role role = roleService.findById(roleId);
        mav.addObject("role", role);
        mav.setViewName("role-show");
        return mav;
    }

    /**
     * 通过角色id删除角色以及移除角色与其它两张表的关联关系
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/delete.do")
    public String delete(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }

    /**
     * 根据角色id 查询出角色和可以添加的权限
     *
     * @param roleId
     * @return
     */
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id", required = true) String roleId) throws Exception {
        ModelAndView mav = new ModelAndView();
        //根据用户id查询userInfo
        Role role = roleService.findById(roleId);
        mav.addObject("role", role);
        //根据用户id查询出可以添加的角色
        List<Permission> permissions = roleService.findRoleByIdAndAllPermission(roleId);
        mav.addObject("permissionList", permissions);
        //跳转到视图解析器所在的页面
        mav.setViewName("role-permission-add");
        return mav;
    }

    /**
     * 给角色添加权限
     *
     * @param roleId
     * @param permissionIds
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId", required = true) String roleId, @RequestParam(name = "ids", required = true) String[] permissionIds) throws Exception {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
