package com.ymsw.customer.service.impl;

import java.util.Date;
import java.util.List;

import com.ymsw.common.annotation.DataScope;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.utils.DateUtils;
import com.ymsw.common.utils.StringUtils;
import com.ymsw.framework.util.ShiroUtils;
import com.ymsw.system.domain.SysDictData;
import com.ymsw.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ymsw.customer.mapper.YmswCustomerMapper;
import com.ymsw.customer.domain.YmswCustomer;
import com.ymsw.customer.service.IYmswCustomerService;
import com.ymsw.common.core.text.Convert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 客户信息表Service业务层处理
 *
 * @author ymsw
 * @date 2020-05-18
 */
@Service
public class YmswCustomerServiceImpl implements IYmswCustomerService {
    @Autowired
    private YmswCustomerMapper ymswCustomerMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询客户信息表
     *
     * @param customerId 客户信息表ID
     * @return 客户信息表
     */
    @Override
    public YmswCustomer selectYmswCustomerById(Long customerId) {
        return ymswCustomerMapper.selectYmswCustomerById(customerId);
    }

    /**
     * 查询客户信息表列表
     *
     * @param ymswCustomer 客户信息表
     * @return 客户信息表
     */
    @Override
//    @DataScope(deptAlias = "d", userAlias = "u")
    public List<YmswCustomer> selectYmswCustomerList(YmswCustomer ymswCustomer) {
        Long userId = ShiroUtils.getUserId();
        ymswCustomer.setUserId(userId);
        return ymswCustomerMapper.selectYmswCustomerList(ymswCustomer);
    }

    /**
     * 新增客户信息表
     *
     * @param ymswCustomer 客户信息表
     * @return 结果
     */
    @Override
    public AjaxResult insertYmswCustomer(YmswCustomer ymswCustomer) {
        //通过手机号查询该手机号的最后一次申请时间
        YmswCustomer dbCustomer = ymswCustomerMapper.selectLastYmswCustomerByPhone(ymswCustomer.getCustomerPhone());
        //如果存在，就查询字典表里允许天数
        if (StringUtils.isNotNull(dbCustomer)) {
            List<SysDictData> allow_days = sysDictDataMapper.selectDictDataByType("allow_days");
            if (StringUtils.isNotEmpty(allow_days)) {
                SysDictData sysDictData = allow_days.get(0);
                String daysValue = sysDictData.getDictValue();//获取允许的天数
                long days1 = DateUtils.getDays(dbCustomer.getApplyTime(), DateUtils.getNowDate());//计算当前的时间和最后一次申请时间的间隔天数
                if (Long.valueOf(daysValue) > days1) {
                    return AjaxResult.error("该客户在"+daysValue+"天内已添加过，不可频繁添加！");
                }
            }
        }
        ymswCustomer.setCustomerStar(0);        //设置星级为0级
        ymswCustomer.setApplyTime(new Date());  //设置申请时间为当前时间
        ymswCustomer.setCustomerType("1");      //设置客户类型为“新客户”  1新客户  2再分配
        ymswCustomer.setCustomerStatus("0");    //设置客户状态为“新申请”
        int i = ymswCustomerMapper.insertYmswCustomer(ymswCustomer);
        if (i > 0){
            return AjaxResult.success();
        }else {
            return AjaxResult.error();
        }
    }

    /**
     * 修改客户信息表
     *
     * @param ymswCustomer 客户信息表
     * @return 结果
     */
    @Override
    public int updateYmswCustomer(YmswCustomer ymswCustomer) {
        return ymswCustomerMapper.updateYmswCustomer(ymswCustomer);
    }

    /**
     * 删除客户信息表对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYmswCustomerByIds(String ids) {
        return ymswCustomerMapper.deleteYmswCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息表信息
     *
     * @param customerId 客户信息表ID
     * @return 结果
     */
    @Override
    public int deleteYmswCustomerById(Long customerId) {
        return ymswCustomerMapper.deleteYmswCustomerById(customerId);
    }
}
