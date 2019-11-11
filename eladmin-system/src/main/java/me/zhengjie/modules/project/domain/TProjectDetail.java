package me.zhengjie.modules.project.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.io.Serializable;

/**
* @author Zzh
* @date 2019-11-08
*/
@Entity
@Data
@Table(name="t_project_detail")
public class TProjectDetail implements Serializable {

    // 项目编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    // 项目名称
    @Column(name = "project_name")
    private String projectName;

    // 省份
    @Column(name = "province")
    private String province;

    // 城市
    @Column(name = "city")
    private String city;

    // 地址
    @Column(name = "address")
    private String address;

    // 开始时间
    @Column(name = "start_time")
    private Timestamp startTime;

    // 结束时间
    @Column(name = "end_time")
    private Timestamp endTime;

    // 出差时长
    @Column(name = "duration")
    private Integer duration;

    // 预算
    @Column(name = "budget")
    private BigDecimal budget;

    // 总报销金额
    @Column(name = "aggregate_amount")
    private BigDecimal aggregateAmount;

    // 总补贴金额
    @Column(name = "subsidy_amount")
    private BigDecimal subsidyAmount;

    // 报销状态 0未报销，1已报销
    @Column(name = "Account_state")
    private Integer accountState;

    // 报销时间
    @Column(name = "Account_time")
    private Timestamp accountTime;

    // 补贴状态 0未报销，1已报销
    @Column(name = "subsidy_state")
    private Integer subsidyState;

    // 补贴报销时间
    @Column(name = "subsidy_name")
    private Timestamp subsidyName;

    // 创建人
    @Column(name = "created_user")
    private Integer createdUser;

    // 创建时间
    @Column(name = "created_time")
    private Timestamp createdTime;

    // 更新人
    @Column(name = "updated_user")
    private Integer updatedUser;

    // 更新时间
    @Column(name = "updated_time")
    private Timestamp updatedTime;

    // 差旅状态 0生效，1结束
    @Column(name = "state")
    private Integer state;

    // 是否有效 0有效，1无效
    @Column(name = "vaild")
    private Integer vaild;

    public void copy(TProjectDetail source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}