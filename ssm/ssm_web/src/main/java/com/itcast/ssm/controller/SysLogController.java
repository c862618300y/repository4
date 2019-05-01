package com.itcast.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 系统日志界面层
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有系统日志信息
     * @return
     */
//    @RolesAllowed("ADMIN")//当前方法只有具有 ADMIN 角色的用户才能访问
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ADMIN')")//当前方法只有具有 ADMIN 角色的用户才能访问
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "10")Integer size) {
        ModelAndView mav = new ModelAndView();
        List<SysLog> sysLogs=sysLogService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(sysLogs);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("syslog-page-list");
        return mav;
    }

    /**
     * 通过日志id 删除系统日志
     * @param sysLogId
     * @return
     */
//    @Secured("ROLE_USER")//当前方法只有具有 USER(注意加上ROLE_) 角色的用户才能访问
    @RequestMapping("/deleteSysLog.do")
    @PreAuthorize("authentication.principal.username=='rose'")//当前方法只有用户名叫rose用户才能访问
    public String deleteSysLog(@RequestParam(name = "id",required = true)String sysLogId){
        sysLogService.deleteSysLog(sysLogId);
        return "redirect:findAll.do";
    }
}
