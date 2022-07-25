package com.tenneling.dao;

import com.tenneling.entity.base.WxUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxUser record);

    WxUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxUser record);

    /**
     * 根据 openId 获取微信用户信息
     *
     * @param openId openId
     * @return 用户实体
     */
    WxUser getWxUserByOpenId(@Param("openId") String openId);
}