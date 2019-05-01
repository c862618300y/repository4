package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

/**
 * 会员持久层接口
 */
public interface MemberDao {
    /**
     * 通过订单表中的会员id查询会员信息
     * @param memberId
     * @return
     */
    @Select("select * from member where id=#{memberId}")
    public Member findById(String memberId)throws Exception;
}
