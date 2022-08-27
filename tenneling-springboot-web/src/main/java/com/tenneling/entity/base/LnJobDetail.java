package com.tenneling.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * 
 * @author wcyong
 * 
 * @date 2022-08-27
 */
@Getter
@Setter
@ToString
public class LnJobDetail {
    /**
     * 任务ID
     */
    private String jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组
     */
    private String jobGroup;

    /**
     * 描述
     */
    private String description;

    /**
     * 类名
     */
    private String beanName;

    /**
     * 时间表达式
     */
    private String cron;

    /**
     * 状态
     */
    private String status;

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