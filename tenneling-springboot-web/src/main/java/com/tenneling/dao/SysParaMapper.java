package com.tenneling.dao;

import com.tenneling.entity.base.SysPara;
import org.springframework.stereotype.Repository;

@Repository
public interface SysParaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPara record);

    SysPara selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPara record);

    SysPara getByKey(String key);

}