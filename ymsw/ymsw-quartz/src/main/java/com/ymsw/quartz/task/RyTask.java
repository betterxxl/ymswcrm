package com.ymsw.quartz.task;

import com.ymsw.customer.mapper.YmswCollectionPoolMapper;
import com.ymsw.customer.mapper.YmswCustomerMapper;
import com.ymsw.customer.service.IYmswCustomerService;
import com.ymsw.customer.service.impl.YmswCollectionPoolServiceImpl;
import com.ymsw.system.domain.SysConfig;
import com.ymsw.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ymsw.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
                List<String> customerIds = ymswCustomerMapper.selectAutoReallocIds(days);   //查询出需要自动抽回的客户ids
                if (StringUtils.isNotEmpty(customerIds)){
                    ymswCustomerMapper.updateUseridToNull(customerIds); //1、将客户ids对应的user_id批量设置为null
                    //2、同时批量添加到公共池  参数“2”是公共池，操作人id设置为null
                    ymswCollectionPoolMapper.batchInsertYmswCollectionPool(YmswCollectionPoolServiceImpl.getAddList(customerIds,"2",null));
                }
            }
        }
    }
}
