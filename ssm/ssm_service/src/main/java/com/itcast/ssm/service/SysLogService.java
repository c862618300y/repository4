package com.itcast.ssm.service;

import com.itcast.ssm.domain.SysLog;

import java.util.List;

/**
 * 系统日志业务层接口
 */
public interface SysLogService {
    /**
     * 添加系统日志到数据库
     * @param sysLog
     * @throws Exception
     */
    public void save(SysLog sysLog)throws Exception;

    /**
     * 查询所有系统日志信息
     * @return
     * @param page
     * @param size
     */
    List<SysLog> findAll(int page, int size);

    /**
     * 通过日志id 删除系统日志
     * @param sysLogId
     */
    void deleteSysLog(String sysLogId);
}
