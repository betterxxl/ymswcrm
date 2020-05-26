package com.ymsw.web.controller.system;

import com.ymsw.common.core.controller.BaseController;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.customer.domain.YmswCustomer;
import com.ymsw.customer.service.IYmswCustomerService;
import com.ymsw.framework.config.AccessPhoneConfig;
import com.ymsw.system.domain.SysUser;
import com.ymsw.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
   @RequestMapping("addData")
   public AjaxResult incomingFromOther(HttpServletRequest request){
       String customerName = request.getParameter("customerName");
       String customerSex = request.getParameter("customerSex");
       String customerPhone = request.getParameter("customerPhone");
       String customerQuota = request.getParameter("customerQuota");
       YmswCustomer ymswCustomer =new YmswCustomer();
       //获取能分配的用户列表
       List<SysUser> sysUserList = iSysUserService.selectUsersForIncomingData(accessPhoneConfig.getUserIds());
      //随机进行分配
       int i = (int)(Math.random()*42)+1;
       System.out.println("i = " + i);
       SysUser sysUser = sysUserList.get(i-1);
       ymswCustomer.setUserId(sysUser.getUserId());
       ymswCustomer.setCustomerName(customerName);
       ymswCustomer.setCustomerSex(customerSex);
       ymswCustomer.setCustomerPhone(customerPhone);
       ymswCustomer.setCustomerQuota(Integer.valueOf(customerQuota));
        iYmswCustomerService.insertYmswCustomer(ymswCustomer, "incoming data");
       return  AjaxResult.success();
   }
}
