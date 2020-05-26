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
     * 查询客户信息表列表
     * 
     * @param ymswCustomer 客户信息表
     * @return 客户信息表集合
     */
    public List<YmswCustomer> selectYmswCustomerList(YmswCustomer ymswCustomer);

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
}
