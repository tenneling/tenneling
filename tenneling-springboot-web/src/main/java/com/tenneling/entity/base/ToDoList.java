package com.tenneling.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @date 2022-07-30
 */
@Getter
@Setter
@ToString
@Entity(name = "to_do_list")
public class ToDoList {
    private static final long serialVersionUID = -6923218526268523232L;

    /**
     * 流水ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户ID
     */
    private String openid;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 是否提醒
     */
    private String isAlert;

    /**
     * 状态
     */
    private String status;

    /**
     * 内容文本
     */
    private String content;

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