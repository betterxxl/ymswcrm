package com.ymsw.customer.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ymsw.customer.domain.YmswRemark;
import com.ymsw.customer.service.IYmswRemarkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ymsw.common.annotation.Log;
import com.ymsw.common.enums.BusinessType;
import com.ymsw.customer.domain.YmswCustomer;
import com.ymsw.customer.service.IYmswCustomerService;
import com.ymsw.common.core.controller.BaseController;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.utils.poi.ExcelUtil;
import com.ymsw.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客户信息表Controller
 * 
 * @author ymsw
 * @date 2020-05-18
 */
@Controller
@RequestMapping("/customer/main")
public class YmswCustomerController extends BaseController
{
    private String prefix = "customer/main";

    @Autowired
    private IYmswCustomerService ymswCustomerService;
    @Autowired
    private IYmswRemarkService ymswRemarkService;

    /**
     * 跳转到 所有客户 -→ 我的客户页面
     */
    @RequiresPermissions("customer:main:view")
    @GetMapping()
    public String main()
    {
        return prefix + "/main";
    }

    /**
     * 我的客户查询客户列表（通过userId查询）
     */
    @RequiresPermissions("customer:main:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YmswCustomer ymswCustomer)
    {
        startPage();
        List<YmswCustomer> list = ymswCustomerService.selectYmswCustomerList(ymswCustomer);
        return getDataTable(list);
    }

    /**
     * 跳转到 客户管理 -→ 客户列表页面
     */
    @RequiresPermissions("customer:manage:view")
    @GetMapping("/manage")
    public String manage()
    {
        return prefix + "/manage";
    }

    /**
     * 客户管理 -→ 客户列表页面 查询客户列表（使用数据范围）
     */
    @RequiresPermissions("customer:manage:list")
    @PostMapping("/manamelist")
    @ResponseBody
    public TableDataInfo manamelist(YmswCustomer ymswCustomer)
    {
        startPage();
        List<YmswCustomer> list = ymswCustomerService.selectManameList(ymswCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户信息表列表
     */
    @RequiresPermissions("customer:main:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YmswCustomer ymswCustomer)
    {
        List<YmswCustomer> list = ymswCustomerService.selectManameList(ymswCustomer);
        ExcelUtil<YmswCustomer> util = new ExcelUtil<YmswCustomer>(YmswCustomer.class);
        return util.exportExcel(list, "main");
    }

    /**
     * 新增客户信息表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户信息表
     */
    @RequiresPermissions("customer:main:add")
    @Log(title = "客户信息表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated YmswCustomer ymswCustomer, String type)
    {
        return ymswCustomerService.insertYmswCustomer(ymswCustomer,type);
    }

    /**
     * 修改客户信息表
     */
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        //通过customerId查询客户信息
        YmswCustomer ymswCustomer = ymswCustomerService.selectYmswCustomerById(customerId);
        //计算年龄
        String customerBirth = ymswCustomer.getCustomerBirth();
        Calendar date = Calendar.getInstance();
        int age = Integer.valueOf(date.get(Calendar.YEAR))-Integer.valueOf(customerBirth);
        ymswCustomer.setCustomerBirth(String.valueOf(age));//赋值age为年龄
        //通过customerId查询该客户的业务经理添加的备注
        YmswRemark ymswRemark = new YmswRemark();
        ymswRemark.setCustomerId(customerId);
        ymswRemark.setIsCharge("0");//是否主管 0否 1是
        List<YmswRemark> ymswRemarks = ymswRemarkService.selectYmswRemarkList(ymswRemark);
        //查询该客户的主管添加的点评
        ymswRemark.setIsCharge("1");//是否主管 0否 1是
        List<YmswRemark> remarkList = ymswRemarkService.selectYmswRemarkList(ymswRemark);
        mmap.put("ymswRemarks",ymswRemarks);
        mmap.put("remarkList",remarkList);
        mmap.put("ymswCustomer", ymswCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户信息表
     */
    @RequiresPermissions("customer:main:edit")
    @Log(title = "客户信息表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated YmswCustomer ymswCustomer)
    {
        return toAjax(ymswCustomerService.updateYmswCustomer(ymswCustomer));
    }

    /**
     * 删除客户信息表
     */
    @RequiresPermissions("customer:main:remove")
    @Log(title = "客户信息表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ymswCustomerService.deleteYmswCustomerByIds(ids));
    }

    /**
     * 下载模板
     */
    @RequiresPermissions("customer:main:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<YmswCustomer> util = new ExcelUtil<YmswCustomer>(YmswCustomer.class);
        return util.importTemplateExcel("客户数据");
    }

    /**
     * 导入客户数据
     */
    @Log(title = "客户信息表", businessType = BusinessType.IMPORT)
    @RequiresPermissions("customer:main:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<YmswCustomer> util = new ExcelUtil<>(YmswCustomer.class);
        List<YmswCustomer> ymswCustomerList = util.importExcel(file.getInputStream());
        String message = ymswCustomerService.importYmswCustomer(ymswCustomerList);
        return AjaxResult.success(message);
    }

    /**
     * 根据客户电话号码查询客户信息（客户表里可能存在多条相同的电话号码，取最后一次添加的客户信息【即申请时间是最大的】）
     */
//    @RequiresPermissions("customer:main:getCustomerInfo")
    @GetMapping("/getCustomerInfo/{customerPhone}")
    @ResponseBody
    public AjaxResult getCustomerInfo(@PathVariable("customerPhone") String customerPhone)
    {
        YmswCustomer ymswCustomer = ymswCustomerService.getCustomerInfo(customerPhone);
        return AjaxResult.success(ymswCustomer);
    }
}
