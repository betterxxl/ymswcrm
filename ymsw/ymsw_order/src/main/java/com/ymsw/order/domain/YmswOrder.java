package com.ymsw.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ymsw.common.annotation.Excel;
import com.ymsw.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 订单信息表对象 ymsw_order
 * 
 * @author ymsw
 * @date 2020-05-22
 */
public class YmswOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单号 */
    private Long orderId;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 合同id */
    @Excel(name = "合同id")
    private Long contractId;

    /** 合同图片路径 */
    @Excel(name = "合同图片路径")
    private String contractPath;

    /** 添加签约日期 */
    @Excel(name = "添加签约日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addTime;

    /** 订单状态1 已签约2 已进件3 已批款4 已收款5 已拒绝 */
    @Excel(name = "订单状态1 已签约2 已进件3 已批款4 已收款5 已拒绝")
    private String orderStatus;

    /** 批款额度(万元) */
    @Excel(name = "批款额度(万元)")
    private Integer allowAmount;

    /** 创收(元) */
    @Excel(name = "创收(元)")
    private Double incomeGeneration;

    /** 费率(%) */
    @Excel(name = "费率(%)")
    private Double orderRate;

    /** 诚意金(元) */
    @Excel(name = "诚意金(元)")
    private Integer sincerityMoney;

    /** 诚意金收款方式1 微信    2 支付宝  3 转账  4 现金 */
    @Excel(name = "诚意金收款方式1 微信    2 支付宝  3 转账  4 现金")
    private String sincerityPayMethod;

    /** 进件渠道 */
    @Excel(name = "进件渠道")
    private String incomingChannel;

    /** 渠道费(元) */
    @Excel(name = "渠道费(元)")
    private Double channelFee;

    /** 进件类型1信用贷2房抵押3车抵押4合作单 */
    @Excel(name = "进件类型1信用贷2房抵押3车抵押4合作单")
    private String incomingType;

    /** 进件助理1 邓娟2 黄文莎3 陈梦颖4 李紫馨5 其他 */
    @Excel(name = "进件助理1 邓娟2 黄文莎3 陈梦颖4 李紫馨5 其他")
    private String incomingAssistant;

    /** 银行经理 */
    @Excel(name = "银行经理")
    private String bankManager;

    /** 订单备注 */
    @Excel(name = "订单备注")
    private String orderRemark;

    /** 客户经理id */
    @Excel(name = "客户经理id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setContractPath(String contractPath) 
    {
        this.contractPath = contractPath;
    }

    public String getContractPath() 
    {
        return contractPath;
    }
    public void setAddTime(Date addTime) 
    {
        this.addTime = addTime;
    }

    public Date getAddTime() 
    {
        return addTime;
    }
    public void setOrderStatus(String orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() 
    {
        return orderStatus;
    }
    public void setAllowAmount(Integer allowAmount) 
    {
        this.allowAmount = allowAmount;
    }

    public Integer getAllowAmount() 
    {
        return allowAmount;
    }
    public void setIncomeGeneration(Double incomeGeneration) 
    {
        this.incomeGeneration = incomeGeneration;
    }

    public Double getIncomeGeneration() 
    {
        return incomeGeneration;
    }
    public void setOrderRate(Double orderRate) 
    {
        this.orderRate = orderRate;
    }

    public Double getOrderRate() 
    {
        return orderRate;
    }
    public void setSincerityMoney(Integer sincerityMoney) 
    {
        this.sincerityMoney = sincerityMoney;
    }

    public Integer getSincerityMoney() 
    {
        return sincerityMoney;
    }
    public void setSincerityPayMethod(String sincerityPayMethod) 
    {
        this.sincerityPayMethod = sincerityPayMethod;
    }

    public String getSincerityPayMethod() 
    {
        return sincerityPayMethod;
    }
    public void setIncomingChannel(String incomingChannel) 
    {
        this.incomingChannel = incomingChannel;
    }

    public String getIncomingChannel() 
    {
        return incomingChannel;
    }
    public void setChannelFee(Double channelFee) 
    {
        this.channelFee = channelFee;
    }

    public Double getChannelFee() 
    {
        return channelFee;
    }
    public void setIncomingType(String incomingType) 
    {
        this.incomingType = incomingType;
    }

    public String getIncomingType() 
    {
        return incomingType;
    }
    public void setIncomingAssistant(String incomingAssistant) 
    {
        this.incomingAssistant = incomingAssistant;
    }

    public String getIncomingAssistant() 
    {
        return incomingAssistant;
    }
    public void setBankManager(String bankManager) 
    {
        this.bankManager = bankManager;
    }

    public String getBankManager() 
    {
        return bankManager;
    }
    public void setOrderRemark(String orderRemark) 
    {
        this.orderRemark = orderRemark;
    }

    public String getOrderRemark() 
    {
        return orderRemark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("customerId", getCustomerId())
            .append("contractId", getContractId())
            .append("contractPath", getContractPath())
            .append("addTime", getAddTime())
            .append("updateTime", getUpdateTime())
            .append("orderStatus", getOrderStatus())
            .append("allowAmount", getAllowAmount())
            .append("incomeGeneration", getIncomeGeneration())
            .append("orderRate", getOrderRate())
            .append("sincerityMoney", getSincerityMoney())
            .append("sincerityPayMethod", getSincerityPayMethod())
            .append("incomingChannel", getIncomingChannel())
            .append("channelFee", getChannelFee())
            .append("incomingType", getIncomingType())
            .append("incomingAssistant", getIncomingAssistant())
            .append("bankManager", getBankManager())
            .append("orderRemark", getOrderRemark())
            .toString();
    }
}
