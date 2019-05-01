package com.itcast.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itcast.ssm.dao.SysLogDao;
import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统日志业务层接口实现类
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 添加系统日志到数据库
     * @param sysLog
     * @throws Exception
     */
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    /**
     * 查询所有系统日志信息
     * @return
     * @param page
     * @param size
     */
    @Override
    public List<SysLog> findAll(int page, int size) {
        //在执行sql前使用PageHelper来完成分页 page:当前页面  size:每页显示的数据条数
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }

    /**
     * 通过日志id 删除系统日志
     * @param sysLogId
     */
    @Override
    public void deleteSysLog(String sysLogId) {
        sysLogDao.deleteSysLog(sysLogId);
    }
}
