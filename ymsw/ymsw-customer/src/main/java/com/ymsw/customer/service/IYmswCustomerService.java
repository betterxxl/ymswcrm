package com.ymsw.customer.service;

import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.customer.domain.YmswCustomer;
import java.util.List;

/**
 * 客户信息表Service接口
 * 
 * @author ymsw
 * @date 2020-05-18
 */
public interface IYmswCustomerService 
{
    /**
     * 查询客户信息表
     * 
     * @param customerId 客户信息表ID
     * @return 客户信息表
     */
    public YmswCustomer selectYmswCustomerById(Long customerId);

    /**
     * 我的客户查询客户列表（通过userId查询）
     * 数据范围：仅查询个人的客户
     * @param ymswCustomer 客户信息表
     * @return 客户信息表集合
     */
    public List<YmswCustomer> selectYmswCustomerList(YmswCustomer ymswCustomer);

    /**
     * 客户管理 -→ 客户列表页面 查询客户列表（使用数据范围）
     * 数据范围：查询客户表里的所有客户（但不包括收藏夹里的客户，而包括公共池里的客户）
     */
    public List<YmswCustomer> selectManageList(YmswCustomer ymswCustomer);

    /**
     * 新增客户信息表
     * 
     * @param ymswCustomer 客户信息表
     * @return 结果
     */
    public AjaxResult insertYmswCustomer(YmswCustomer ymswCustomer,String type);

    /**
     * 修改客户信息表
     * 
     * @param ymswCustomer 客户信息表
     * @return 结果
     */
    public int updateYmswCustomer(YmswCustomer ymswCustomer);

    /**
     * 批量删除客户信息表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYmswCustomerByIds(String ids);

    /**
     * 删除客户信息表信息
     * 
     * @param customerId 客户信息表ID
     * @return 结果
     */
    public int deleteYmswCustomerById(Long customerId);

    /**
     * 导入客户数据
     *
     * @param ymswCustomerList 客户数据列表
     * @return 结果
     */
    String importYmswCustomer(List<YmswCustomer> ymswCustomerList);

    /**
     * 根据客户电话号码查询客户信息（客户表里可能存在多条相同的电话号码，取最后一次添加的客户信息【即申请时间是最大的】）
     */
    YmswCustomer getCustomerInfo(String customerPhone);

    /**
     * 查询出需要自动抽回的客户ids
     */
    List<String> selectAutoReallocIds(String days);

}
