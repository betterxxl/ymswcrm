package com.ymsw.customer.mapper;

import com.ymsw.customer.domain.YmswCustomer;
import java.util.List;

/**
 * 客户信息表Mapper接口
 * 
 * @author ymsw
 * @date 2020-05-18
 */
public interface YmswCustomerMapper 
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
    public int insertYmswCustomer(YmswCustomer ymswCustomer);

    /**
     * 修改客户信息表
     * 
     * @param ymswCustomer 客户信息表
     * @return 结果
     */
    public int updateYmswCustomer(YmswCustomer ymswCustomer);

    /**
     * 删除客户信息表
     * 
     * @param customerId 客户信息表ID
     * @return 结果
     */
    public int deleteYmswCustomerById(Long customerId);

    /**
     * 批量删除客户信息表
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYmswCustomerByIds(String[] customerIds);

    /**
     * 通过手机号码查询该客户最后一次申请时间的记录
     *
     * @param customerPhone 客户手机号码
     * @return 结果
     */
    public YmswCustomer selectLastYmswCustomerByPhone(String customerPhone);
}
