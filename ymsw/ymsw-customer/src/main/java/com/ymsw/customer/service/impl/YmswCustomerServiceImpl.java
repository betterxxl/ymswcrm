package com.ymsw.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymsw.customer.mapper.YmswCustomerMapper;
import com.ymsw.customer.domain.YmswCustomer;
import com.ymsw.customer.service.IYmswCustomerService;
import com.ymsw.common.core.text.Convert;

/**
 * 客户信息表Service业务层处理
 * 
 * @author ymsw
 * @date 2020-05-18
 */
@Service
public class YmswCustomerServiceImpl implements IYmswCustomerService 
{
    @Autowired
    private YmswCustomerMapper ymswCustomerMapper;

    /**
     * 查询客户信息表
     * 
     * @param customerId 客户信息表ID
     * @return 客户信息表
     */
    @Override
    public YmswCustomer selectYmswCustomerById(Long customerId)
    {
        return ymswCustomerMapper.selectYmswCustomerById(customerId);
    }

    /**
     * 查询客户信息表列表
     * 
     * @param ymswCustomer 客户信息表
     * @return 客户信息表
     */
    @Override
    public List<YmswCustomer> selectYmswCustomerList(YmswCustomer ymswCustomer)
    {
        return ymswCustomerMapper.selectYmswCustomerList(ymswCustomer);
    }

    /**
     * 新增客户信息表
     * 
     * @param ymswCustomer 客户信息表
     * @return 结果
     */
    @Override
    public int insertYmswCustomer(YmswCustomer ymswCustomer)
    {
        return ymswCustomerMapper.insertYmswCustomer(ymswCustomer);
    }

    /**
     * 修改客户信息表
     * 
     * @param ymswCustomer 客户信息表
     * @return 结果
     */
    @Override
    public int updateYmswCustomer(YmswCustomer ymswCustomer)
    {
        return ymswCustomerMapper.updateYmswCustomer(ymswCustomer);
    }

    /**
     * 删除客户信息表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYmswCustomerByIds(String ids)
    {
        return ymswCustomerMapper.deleteYmswCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息表信息
     * 
     * @param customerId 客户信息表ID
     * @return 结果
     */
    @Override
    public int deleteYmswCustomerById(Long customerId)
    {
        return ymswCustomerMapper.deleteYmswCustomerById(customerId);
    }
}
