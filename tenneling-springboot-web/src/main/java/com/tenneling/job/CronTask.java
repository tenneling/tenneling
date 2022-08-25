package com.tenneling.job;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Transient;
import java.util.Map;

@Getter
@Setter
@ToString
public class CronTask {
    /** 任务名称 */
    private String jobName;

    /** 参数 */
    private String parameter;

    /** 任务分组 1:   2： */
    private Long type;

    /** 任务分组名 */
    private String typeName;

    /** 调度规则：0/5 * * * * ? */
    private String cronExpression;

    /**  任务执行时调用哪个类的方法 包名+类名 */
    private String beanClass;

    /** 任务描述 */
    private String description;

    /**  任务状态 */
    private Long jobStatus;

    /** 0 未删除   1 删除 */
    private Long deleteFlag;

    /**参数的map*/
    @Transient
    private Map<String,String> parameterMap;
}
