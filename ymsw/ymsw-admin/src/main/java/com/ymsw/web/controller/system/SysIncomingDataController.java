package com.ymsw.web.controller.system;

import com.ymsw.common.core.controller.BaseController;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.json.JSONObject;
import com.ymsw.common.utils.StringUtils;
import com.ymsw.common.utils.http.HttpUtils;
import com.ymsw.customer.domain.YmswCustomer;
import com.ymsw.customer.service.IYmswCustomerService;
import com.ymsw.framework.config.AccessPhoneConfig;
import com.ymsw.framework.web.service.DictService;
import com.ymsw.quota.domain.QuotaManager;
import com.ymsw.quota.mapper.QuotaManagerMapper;
import com.ymsw.system.domain.SysDictData;
import com.ymsw.system.domain.SysUser;
import com.ymsw.system.service.ISysDictDataService;
import com.ymsw.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 引流数据控制器
 * @author Administrator on 2020/5/25.
 * @version 1.0
 */
@RestController
@RequestMapping("/incomingData/main")
public class SysIncomingDataController extends BaseController {
    @Autowired
    IYmswCustomerService iYmswCustomerService;
    @Autowired
    AccessPhoneConfig accessPhoneConfig;
    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    ISysDictDataService sysDictDataService;
    @Autowired
    private QuotaManagerMapper quotaManagerMapper;

    /**
     * 从蜘蛛接口取数据
     * @param request
     * @return
     */
    @RequestMapping("addData")
    @CrossOrigin("http://47.114.135.94:8080")
    public AjaxResult incomingFromSpide(HttpServletRequest request){
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictLabel("incoming_data_status");
        sysDictData.setDictType("ymsw_config");
        List<SysDictData> sysDictDataList = sysDictDataService.selectDictDataList(sysDictData);
        sysDictData = sysDictDataList.get(0);
        if("0".equals(sysDictData.getDictValue()) ){
            return  AjaxResult.error("接口数据已暂停，暂不引入数据");
        }
        String customerName = request.getParameter("customerName"); //客户姓名
        String customerSex = request.getParameter("customerSex");   //客户性别
        String customerPhone = request.getParameter("customerPhone"); //客户手机号
        String customerQuota = request.getParameter("customerQuota"); //借款额度
        String customerCity = request.getParameter("customerCity");   //城市
        String customerBirth = request.getParameter("customerBirth"); //出生年份  1998
        String hasCreditCard = request.getParameter("hasCreditCard"); //有无信用卡(有/无/未知)
        String hasHouse = request.getParameter("hasHouse");  // 有无房(有/无/未知)
        String hasCar = request.getParameter("hasCar");    //有无车(有/无/未知)
        String hasAccumulation = request.getParameter("hasAccumulation");  //有无公积金(有/无/未知)
        String hasSocial = request.getParameter("hasSocial");   //  有无社保(有/无/未知)
        String hasWarranty = request.getParameter("hasWarranty");  // 有无保单(有/无/未知)
        String hasWeilidai = request.getParameter("hasWeilidai"); // 微粒贷(有/无/未知)
        String isOverdue = request.getParameter("isOverdue"); // 逾期(有/无/未知)
        String customerOccupation = request.getParameter("hasCar"); // 职业(上班/做生意/未知)
        String customerSalary = request.getParameter("customerSalary"); // 薪资方式(代发/转账/现金/未知)
        String channel = request.getParameter("channel"); //渠道
        System.out.println("customerName = " + customerName);
        System.out.println("customerPhone = " + customerPhone);
        if(StringUtils.isEmpty(customerName)||StringUtils.isEmpty(customerPhone)||StringUtils.isEmpty(customerQuota)){
            return  AjaxResult.error("缺少参数,请检查");
        }
        YmswCustomer ymswCustomer =new YmswCustomer();
        //获取能分配的用户列表
        List<SysUser> sysUserList = iSysUserService.selectIsDistributeUsers();
        Long userId;
        //当userId不为null时停止循环
        do {
            userId = getUserId(sysUserList);
        }while (StringUtils.isNull(userId));
        ymswCustomer.setUserId(userId);
        ymswCustomer.setCustomerName(customerName);
        ymswCustomer.setCustomerSex(customerSex);
        ymswCustomer.setCustomerPhone(customerPhone);
        ymswCustomer.setCustomerQuota(Integer.valueOf(customerQuota));
        ymswCustomer.setCustomerCity(customerCity);
        ymswCustomer.setCustomerBirth(customerBirth);
        ymswCustomer.setHasCreditCard(hasCreditCard);
        ymswCustomer.setHasHouse(hasHouse);
        ymswCustomer.setHasCar(hasCar);
        ymswCustomer.setHasAccumulation(hasAccumulation);
        ymswCustomer.setHasSocial(hasSocial);
        ymswCustomer.setHasWarranty(hasWarranty);
        ymswCustomer.setHasWeilidai(hasWeilidai);
        ymswCustomer.setIsOverdue(isOverdue);
        ymswCustomer.setCustomerOccupation(customerOccupation);
        ymswCustomer.setCustomerSalary(customerSalary);
        ymswCustomer.setChannel(channel);
        QuotaManager quotaManager = quotaManagerMapper.selectQuotaManagerByUserId(userId);// 查询该userId的配额信息
        return  iYmswCustomerService.insertCustomer(ymswCustomer, quotaManager);
    }
    @RequestMapping("outPutData")
    public  String outPutData(){
        String url="https://www.duodaiwang.com/spider/customer/addCustomer";
        String param="name=test&mobile=18627058902&city=杭州&car=true&age=32&house=true&baodan_is=false&sex=男&money=20&source=英茂商务&shebao=true&gongjijin=false&isbankpay=true&qualification=null";
        String s = HttpUtils.sendPost(url, param,"application/x-www-form-urlencoded");
        System.out.println("s = " + s);
        return  s;
    }
    //获取可以分配客户的userId
    private Long getUserId(List<SysUser> sysUserList){
        //随机进行分配
        int i = (int)(Math.random()*sysUserList.size())+1;
        SysUser sysUser = sysUserList.get(i-1);
        Long userId = sysUser.getUserId();
        QuotaManager quotaManager = quotaManagerMapper.selectQuotaManagerByUserId(userId);//查询该userId的配额信息
        //如果配额信息存在，就进行判断是否允许添加客户，如果不存在，就添加一条配额信息，并返回userId
        if (StringUtils.isNotNull(quotaManager)){
            String quotaStatus = quotaManager.getQuotaStatus();
            Integer allowTodayCount = quotaManager.getAllowTodayCount();//今日配额数
            Integer nowTodayCount = quotaManager.getNowTodayCount();//今日已分配客户数
            Integer allowTotalCount = quotaManager.getAllowTotalCount();    //总限额数
            Integer nowTotalCount = quotaManager.getNowTotalCount();//当前客户数
            boolean notAllow = nowTodayCount + 1 > allowTodayCount ? true : false;
            boolean notAllow2 = nowTotalCount + 1 > allowTotalCount ? true : false;
            // 如果配额状态是关闭，就不分配客户  配额状态 0 关闭 1开启
            if ("0".equals(quotaStatus)){
                return null;
            }
            if (notAllow || notAllow2){
                return null;
            }
        }else {
            quotaManager = new QuotaManager();
            quotaManager.setUserId(userId.intValue());
            quotaManager.setAllowTotalCount(500);//总限额数
            quotaManager.setNowTotalCount(0);//当前客户数
            quotaManager.setAllowTodayCount(5);//今日配额数
            quotaManager.setNowTodayCount(0);//今日已分配客户数
            quotaManager.setQuotaStatus("1");//配额状态0 关闭 1开启
            quotaManager.setUserId(userId.intValue());
            quotaManagerMapper.insertQuotaManager(quotaManager);//添加一条配额信息
        }
        return userId;
    }
}
