package com.ymsw.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ymsw.common.annotation.Excel;
import com.ymsw.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 客户信息表对象 ymsw_customer
 * 
 * @author ymsw
 * @date 2020-05-18
 */
public class YmswCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id编号 */
    private Long customerId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String customerName;

    /** 性别 0男 1女 2未知 */
    @Excel(name = "性别 0男 1女 2未知")
    private String customerSex;

    /** 手机号 */
    @Excel(name = "手机号")
    private String customerPhone;

    /** 客户状态0新申请1待跟进2已邀约3已签约4已放款5已拒绝6资质不符7捣乱申请8外地申请9黑名单 */
    @Excel(name = "客户状态0新申请1待跟进2已邀约3已签约4已放款5已拒绝6资质不符7捣乱申请8外地申请9黑名单")
    private String customerStatus;

    /** 出生日期 */
    @Excel(name = "出生日期", width = 30)
    private String customerBirth;

    /** 额度(万) */
    @Excel(name = "额度(万)")
    private Integer customerQuota;

    /** 星级 */
    @Excel(name = "星级")
    private Integer customerStar;

    /** 申请时间 */
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 有无房0无1有2 未知 */
    @Excel(name = "有无房0无1有2 未知")
    private String hasHouse;

    /** 有无车0无1有2 未知 */
    @Excel(name = "有无车0无1有2 未知")
    private String hasCar;

    /** 有无保单0无1有2 未知 */
    @Excel(name = "有无保单0无1有2 未知")
    private String hasWarranty;

    /** 微粒贷0无1有2未知 */
    @Excel(name = "微粒贷0无1有2未知")
    private String hasWeilidai;

    /** 信用卡0无1有2未知 */
    @Excel(name = "信用卡0无1有2未知")
    private String hasCreditCard;

    /** 公积金0无1有2未知 */
    @Excel(name = "公积金0无1有2未知")
    private String hasAccumulation;

    /** 有无社保0无1有2未知 */
    @Excel(name = "有无社保0无1有2未知")
    private String hasSocial;

    /** 逾期0无1有2未知 */
    @Excel(name = "逾期0无1有2未知")
    private String isOverdue;

    /** 客户类型1 新客户2 再分配客户 */
    @Excel(name = "客户类型1 新客户2 再分配客户")
    private String customerType;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 职业1上班2 做生意3 未知 */
    @Excel(name = "职业1上班2 做生意3 未知")
    private String customerOccupation;

    /** 薪资方式1代发2转账3现金4未知 */
    @Excel(name = "薪资方式1代发2转账3现金4未知")
    private String customerSalary;

    /** null */
    @Excel(name = "null")
    private String channel;

    /** null */
    @Excel(name = "null", width = 30, dateFormat = "yyyy-MM-dd")
    private Date remarkTime;

    /** null */
    @Excel(name = "null", width = 30, dateFormat = "yyyy-MM-dd")
    private Date distributeTime;

    /** 归属顾问名字 */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setCustomerName(String customerName) 
    {
        this.customerName = customerName;
    }

    public String getCustomerName() 
    {
        return customerName;
    }
    public void setCustomerSex(String customerSex) 
    {
        this.customerSex = customerSex;
    }

    public String getCustomerSex() 
    {
        return customerSex;
    }
    public void setCustomerPhone(String customerPhone) 
    {
        this.customerPhone = customerPhone;
    }

    public Integer getCustomerStar() {
        return customerStar;
    }

    public void setCustomerStar(Integer customerStar) {
        this.customerStar = customerStar;
    }

    public String getCustomerPhone()
    {
        return customerPhone;
    }
    public void setCustomerStatus(String customerStatus) 
    {
        this.customerStatus = customerStatus;
    }

    public String getCustomerStatus() 
    {
        return customerStatus;
    }
    public void setCustomerBirth(String customerBirth)
    {
        this.customerBirth = customerBirth;
    }

    public String getCustomerBirth()
    {
        return customerBirth;
    }
    public void setCustomerQuota(Integer customerQuota) 
    {
        this.customerQuota = customerQuota;
    }

    public Integer getCustomerQuota() 
    {
        return customerQuota;
    }
    public void setApplyTime(Date applyTime) 
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() 
    {
        return applyTime;
    }
    public void setHasHouse(String hasHouse) 
    {
        this.hasHouse = hasHouse;
    }

    public String getHasHouse() 
    {
        return hasHouse;
    }
    public void setHasCar(String hasCar) 
    {
        this.hasCar = hasCar;
    }

    public String getHasCar() 
    {
        return hasCar;
    }
    public void setHasWarranty(String hasWarranty) 
    {
        this.hasWarranty = hasWarranty;
    }

    public String getHasWarranty() 
    {
        return hasWarranty;
    }
    public void setHasWeilidai(String hasWeilidai) 
    {
        this.hasWeilidai = hasWeilidai;
    }

    public String getHasWeilidai() 
    {
        return hasWeilidai;
    }
    public void setHasCreditCard(String hasCreditCard) 
    {
        this.hasCreditCard = hasCreditCard;
    }

    public String getHasCreditCard() 
    {
        return hasCreditCard;
    }
    public void setHasAccumulation(String hasAccumulation) 
    {
        this.hasAccumulation = hasAccumulation;
    }

    public String getHasAccumulation() 
    {
        return hasAccumulation;
    }
    public void setIsOverdue(String isOverdue) 
    {
        this.isOverdue = isOverdue;
    }

    public String getHasSocial() {
        return hasSocial;
    }

    public void setHasSocial(String hasSocial) {
        this.hasSocial = hasSocial;
    }

    public String getIsOverdue()
    {
        return isOverdue;
    }
    public void setCustomerType(String customerType) 
    {
        this.customerType = customerType;
    }

    public String getCustomerType() 
    {
        return customerType;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCustomerOccupation(String customerOccupation) 
    {
        this.customerOccupation = customerOccupation;
    }

    public String getCustomerOccupation() 
    {
        return customerOccupation;
    }
    public void setCustomerSalary(String customerSalary) 
    {
        this.customerSalary = customerSalary;
    }

    public String getCustomerSalary() 
    {
        return customerSalary;
    }
    public void setChannel(String channel) 
    {
        this.channel = channel;
    }

    public String getChannel() 
    {
        return channel;
    }
    public void setRemarkTime(Date remarkTime) 
    {
        this.remarkTime = remarkTime;
    }

    public Date getRemarkTime() 
    {
        return remarkTime;
    }
    public void setDistributeTime(Date distributeTime) 
    {
        this.distributeTime = distributeTime;
    }

    public Date getDistributeTime() 
    {
        return distributeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("customerId", getCustomerId())
            .append("customerName", getCustomerName())
            .append("customerSex", getCustomerSex())
            .append("customerPhone", getCustomerPhone())
            .append("customerStatus", getCustomerStatus())
            .append("customerBirth", getCustomerBirth())
            .append("customerQuota", getCustomerQuota())
            .append("applyTime", getApplyTime())
            .append("hasHouse", getHasHouse())
            .append("hasCar", getHasCar())
            .append("hasWarranty", getHasWarranty())
            .append("hasWeilidai", getHasWeilidai())
            .append("hasCreditCard", getHasCreditCard())
            .append("hasAccumulation", getHasAccumulation())
            .append("isOverdue", getIsOverdue())
            .append("customerType", getCustomerType())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .append("customerOccupation", getCustomerOccupation())
            .append("customerSalary", getCustomerSalary())
            .append("channel", getChannel())
            .append("remarkTime", getRemarkTime())
            .append("distributeTime", getDistributeTime())
            .toString();
    }
}
