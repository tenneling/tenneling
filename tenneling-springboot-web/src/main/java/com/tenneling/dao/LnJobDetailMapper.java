package com.tenneling.dao;

import com.tenneling.entity.base.LnJobDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LnJobDetailMapper {
    int deleteByPrimaryKey(String jobId);

    int insert(LnJobDetail record);

    LnJobDetail selectByPrimaryKey(String jobId);

    int updateByPrimaryKeySelective(LnJobDetail record);

    List<LnJobDetail> selectAllList();

}