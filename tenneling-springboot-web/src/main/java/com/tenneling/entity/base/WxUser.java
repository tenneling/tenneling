package com.tenneling.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @ Description:微信用户实体类
 * @ Author     :wenyanni
 * @ Date       :2022/7/24 14:40
 */
@Getter
@Setter
@ToString
@Entity(name = "wx_user")
public class WxUser implements Serializable {

    private static final long serialVersionUID = -6923218526268543062L;

    /**
     * 流水ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 手机号码
     */
    private String phonenumber;

    private String openid;

    private String nickname;

    /**
     * 性别
     */
    private String gender;

    /**
     * 国别
     */
    private String country;

    /**
     * 城市
     */
    private String city;

    /**
     * 省份
     */
    private String province;

    private String avatarurl;

    private String unionid;

    private String watermark;

    private String rawdata;

    private String cloudid;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 邮箱
     */
    private String email;


    private String language;

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