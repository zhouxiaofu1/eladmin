package me.zhengjie.modules.project.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;


/**
* @author Zzh
* @date 2019-11-08
*/
@Data
public class TProjectDetailDTO implements Serializable {

    // 项目编号
    private Integer projectId;

    // 项目名称
    private String projectName;

    // 省份
    private String province;

    // 城市
    private String city;

    // 地址
    private String address;

    // 开始时间
    private Timestamp startTime;

    // 结束时间
    private Timestamp endTime;

    // 出差时长
    private Integer duration;

    // 预算
    private BigDecimal budget;

    // 总报销金额
    private BigDecimal aggregateAmount;

    // 总补贴金额
    private BigDecimal subsidyAmount;

    // 报销状态 0未报销，1已报销
    private Integer accountState;

    // 报销时间
    private Timestamp accountTime;

    // 补贴状态 0未报销，1已报销
    private Integer subsidyState;

    // 补贴报销时间
    private Timestamp subsidyName;

    // 创建人
    private Integer createdUser;

    // 创建时间
    private Timestamp createdTime;

    // 更新人
    private Integer updatedUser;

    // 更新时间
    private Timestamp updatedTime;

    // 差旅状态 0生效，1结束
    private Integer state;

    // 是否有效 0有效，1无效
    private Integer vaild;
}