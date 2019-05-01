package com.itcast.ssm.controller;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 权限界面层
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询所有资源权限
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mav = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mav.addObject("permissionList",permissionList);
        mav.setViewName("permission-list");
        return mav;
    }

    /**
     * 添加资源权限
     * @param permission
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }

    /**
     * 通过权限id 查询资源权限详情
     * @param permissionId
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String permissionId) throws Exception {
        ModelAndView mav = new ModelAndView();
        Permission permission=permissionService.findById(permissionId);
        mav.addObject("permission",permission);
        mav.setViewName("permission-show");
        return mav;
    }

    /**
     * 删除资源权限
     * @param permissionId
     * @return
     */
    @RequestMapping("/delete.do")
    public String deletePermission(@RequestParam(name = "id",required = true)String permissionId) throws Exception {
        permissionService.deletePermission(permissionId);
        return "redirect:findAll.do";
    }
}
