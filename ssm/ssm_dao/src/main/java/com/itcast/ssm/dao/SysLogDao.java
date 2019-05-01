package com.itcast.ssm.dao;

import com.itcast.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 系统日志持久层接口
 */
public interface SysLogDao {
    /**
     * 添加系统日志到数据库
     *
     * @param sysLog
     */
    @Insert("insert into sysLog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog)throws Exception;

    /**
     * 查询所有系统日志信息
     * @return
     */
    @Select("select * from sysLog")
    List<SysLog> findAll();

    /**
     * 通过日志id 删除系统日志
     * @param sysLogId
     */
    @Delete("delete from sysLog where id=#{sysLogId}")
    void deleteSysLog(String sysLogId);
}
