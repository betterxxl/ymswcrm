package com.ymsw.quartz.task;

import com.ymsw.customer.domain.YmswCustomer;
import com.ymsw.customer.mapper.YmswCollectionPoolMapper;
import com.ymsw.customer.mapper.YmswCustomerMapper;
import com.ymsw.customer.service.IYmswCustomerService;
import com.ymsw.customer.service.impl.YmswCollectionPoolServiceImpl;
import com.ymsw.quota.domain.QuotaManager;
import com.ymsw.quota.mapper.QuotaManagerMapper;
import com.ymsw.system.domain.SysConfig;
import com.ymsw.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ymsw.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 定时任务调度测试
 * 
 * @author ymsw
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private YmswCustomerMapper ymswCustomerMapper;

    @Autowired
    private YmswCollectionPoolMapper ymswCollectionPoolMapper;

    @Autowired
    private QuotaManagerMapper quotaManagerMapper;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }

    //自动抽回定时任务
    @Transactional
    public void autorealloc()
    {
        SysConfig config = new SysConfig();
        config.setConfigId(4L);// 4 为自动抽回设置的configId，固定不变，注意：不能修改。
        SysConfig sysConfig = configService.autoReallocConfig(config);//查询自动抽回设置
        if (StringUtils.isNotNull(sysConfig)){
            String status = sysConfig.getConfigKey();//获取自动抽回状态
            String days = sysConfig.getConfigValue();//获取自动抽回的天数
            //如果开启了自动抽回，就进行抽回
            if ("1".equals(status)){    //状态 0 关闭  1  开启
                List<YmswCustomer> customerList = ymswCustomerMapper.selectAutoReallocIds(days);   //查询出需要自动抽回的客户ids
                if (StringUtils.isNotEmpty(customerList)){
                    List<String> customerIds = new ArrayList<>();
                    for (YmswCustomer ymswCustomer : customerList) {
                        customerIds.add(String.valueOf(ymswCustomer.getCustomerId()));
                    }
                    ymswCustomerMapper.updateUseridToNull(customerIds); //1、将客户ids对应的user_id批量设置为null
                    //2、同时批量添加到公共池  参数“2”是公共池，操作人id设置为null
                    ymswCollectionPoolMapper.batchInsertYmswCollectionPool(YmswCollectionPoolServiceImpl.getAddList(customerList,"2",null));
                }
            }
        }
        //自动抽回完成后更新当前客户数
        List<Map<String, Long>> list = ymswCustomerMapper.selectCount();//按userId分组查询客户数（查询每个业务经理的客户数量）
        for (Map<String, Long> map : list) {
            Long userId = map.get("userId");//获取userId
            Long totalCount = map.get("totalCount");//获取该userId的客户数量
            QuotaManager quotaManager = quotaManagerMapper.selectQuotaManagerByUserId(userId);//查询该userId的配额信息
            //配额信息如果存在，就修改当前客户数，如果不存在，就添加一条配额信息
            if (StringUtils.isNotNull(quotaManager)){
                quotaManager.setNowTotalCount(totalCount.intValue());
                quotaManagerMapper.updateQuotaManager(quotaManager);
            }else {
                quotaManager = new QuotaManager();
                quotaManager.setUserId(userId.intValue());
                quotaManager.setNowTotalCount(totalCount.intValue());//当前客户数
                quotaManager.setAllowTotalCount(500);   //总限额数
                quotaManager.setAllowTodayCount(5); //今日配额数
                quotaManager.setNowTodayCount(0);//今日已分配客户数
                quotaManager.setQuotaStatus("1");   //配额状态 0 关闭 1开启
                quotaManagerMapper.insertQuotaManager(quotaManager);
            }
        }
    }
}
