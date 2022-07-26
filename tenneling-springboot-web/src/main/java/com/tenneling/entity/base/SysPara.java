package com.tenneling.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 
 * @date 2022-07-26
 */
@Getter
@Setter
@ToString
@Entity(name = "sys_para")
public class SysPara {
    /**
     * 流水ID
     */
    private Integer id;

    /**
     * key
     */
    private String paraKey;

    /**
     * value
     */
    private String paraValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态
     */
    private Date status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private String updateTime;
}