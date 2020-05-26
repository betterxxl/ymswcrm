package com.ymsw.customer.service.impl;

import java.util.Date;
import java.util.List;

import com.ymsw.common.annotation.DataScope;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.exception.BusinessException;
import com.ymsw.common.utils.DateUtils;
import com.ymsw.common.utils.StringUtils;

import com.ymsw.customer.domain.YmswRemark;
import com.ymsw.customer.mapper.YmswRemarkMapper;
import com.ymsw.framework.util.ShiroUtils;
import com.ymsw.system.domain.SysDictData;
import com.ymsw.system.mapper.SysDictDataMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class YmswCustomerServiceImpl implements IYmswCustomerService {
    private static final Logger log = LoggerFactory.getLogger(YmswCustomerServiceImpl.class);

    @Autowired
    private YmswCustomerMapper ymswCustomerMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;
    @Autowired
    private YmswRemarkMapper ymswRemarkMapper;

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
    public AjaxResult insertYmswCustomer(YmswCustomer ymswCustomer,String type) {
        //通过手机号查询该手机号的最后一次申请时间
        YmswCustomer dbCustomer = ymswCustomerMapper.selectLastYmswCustomerByPhone(ymswCustomer.getCustomerPhone());
        //如果存在，就查询字典表里允许天数
        if (StringUtils.isNotNull(dbCustomer)) {
            SysDictData sysDictData = new SysDictData();
            sysDictData.setDictType("ymsw_config");
            sysDictData.setDictLabel("allow_days");
            List<SysDictData> allow_days = sysDictDataMapper.selectDictDataList(sysDictData);
            if (StringUtils.isNotEmpty(allow_days)) {
                String daysValue = allow_days.get(0).getDictValue();//获取允许的天数
                long days1 = DateUtils.getDays(dbCustomer.getApplyTime(), DateUtils.getNowDate());//计算当前的时间和最后一次申请时间的间隔天数
                if (Long.valueOf(daysValue) > days1) {
                    return AjaxResult.error("该客户在" + daysValue + "天内已添加过，不可频繁添加！");
                }
            }
            else {
                return AjaxResult.error("添加失败，系统未配置允许天数！");
            }
        }
        ymswCustomer.setCustomerStar(0);        //设置星级为0级
        ymswCustomer.setApplyTime(DateUtils.getNowDate());  //设置申请时间为当前时间
        ymswCustomer.setCustomerType("1");      //设置客户类型为“新客户”  1新客户  2再分配
        ymswCustomer.setCustomerStatus("0");    //设置客户状态为“新申请”
        //1 引流数据新增客户   随机分配客户    2   系统新增客户分配当前添加人
        if("platform".equals(type)){
            ymswCustomer.setUserId(ShiroUtils.getUserId());//设置归属顾问为添加人（谁添加的，归属顾问就是谁）
        }
        ymswCustomer.setDistributeTime(DateUtils.getNowDate()); //设置分配时间
        int i = ymswCustomerMapper.insertYmswCustomer(ymswCustomer);
        if (i > 0) {
            return AjaxResult.success();
        } else {
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
        //如果备注内容不为空，就添加一条备注记录，同时修改最后备注时间
        String remark = ymswCustomer.getRemark();
        if (StringUtils.isNotEmpty(remark)) {
            Date nowDate = DateUtils.getNowDate();
            YmswRemark ymswRemark = new YmswRemark();
            ymswRemark.setCustomerId(ymswCustomer.getCustomerId());//设置客户id
            ymswRemark.setIsCharge("0");//设置是否主管 0否 1是
            ymswRemark.setRemarkContent(remark);//设置备注内容
            ymswRemark.setRemarkTime(nowDate);//设置备注时间
            ymswRemark.setUserId(ShiroUtils.getUserId());//设置操作人
            ymswRemarkMapper.insertYmswRemark(ymswRemark);
            ymswCustomer.setRemarkTime(nowDate);//设置最后备注时间
        }
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

    /**
     * 导入客户数据
     *
     * @param ymswCustomerList 客户数据列表
     * @return 结果
     */
    @Override
    public String importYmswCustomer(List<YmswCustomer> ymswCustomerList) {
        if (StringUtils.isNull(ymswCustomerList) || ymswCustomerList.size() == 0) {
            throw new BusinessException("导入客户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (YmswCustomer ymswCustomer : ymswCustomerList) {
            try {
                ymswCustomer.setApplyTime(DateUtils.getNowDate());//设置申请时间为当前时间
                //通过手机号查询该手机号的最后一次申请时间
                YmswCustomer dbCustomer = ymswCustomerMapper.selectLastYmswCustomerByPhone(ymswCustomer.getCustomerPhone());
                //如果存在，就查询字典表里允许天数
                if (StringUtils.isNull(dbCustomer)) {
                    ymswCustomerMapper.insertYmswCustomer(ymswCustomer);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、客户 " + ymswCustomer.getCustomerPhone() + " 导入成功");
                } else {
                    SysDictData sysDictData = new SysDictData();
                    sysDictData.setDictType("ymsw_config");
                    sysDictData.setDictLabel("allow_days");
                    List<SysDictData> allow_days = sysDictDataMapper.selectDictDataList(sysDictData);//查询重复添加时允许的天数
                    if (StringUtils.isNotEmpty(allow_days)) {
                        String daysValue = allow_days.get(0).getDictValue();//获取允许的天数
                        long days1 = DateUtils.getDays(dbCustomer.getApplyTime(), DateUtils.getNowDate());//计算当前的时间和最后一次申请时间的间隔天数
                        if (Long.valueOf(daysValue) > days1) {
                            failureNum++;
                            failureMsg.append("<br/>" + failureNum + "、客户 " + ymswCustomer.getCustomerPhone() + "在" + daysValue + "天内已添加过，不可频繁添加！");
                        } else {
                            ymswCustomerMapper.insertYmswCustomer(ymswCustomer);
                            successNum++;
                            successMsg.append("<br/>" + successNum + "、客户 " + ymswCustomer.getCustomerPhone() + " 导入成功");
                        }
                    }else {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、客户 " + ymswCustomer.getCustomerPhone() + "导入失败，系统未配置允许天数！");
                    }
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、客户 " + ymswCustomer.getCustomerPhone() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
