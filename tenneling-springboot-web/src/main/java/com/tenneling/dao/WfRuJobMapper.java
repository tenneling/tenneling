package com.tenneling.dao;

import com.tenneling.entity.base.WfRuJob;

public interface WfRuJobMapper {
    int deleteByPrimaryKey(String serialNo);

    int insert(WfRuJob record);

    WfRuJob selectByPrimaryKey(String serialNo);

    int updateByPrimaryKeySelective(WfRuJob record);
}