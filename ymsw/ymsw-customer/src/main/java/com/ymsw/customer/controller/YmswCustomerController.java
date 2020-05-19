package com.ymsw.customer.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @RequiresPermissions("customer:main:view")
    @GetMapping()
    public String main()
    {
        return prefix + "/main";
    }

    /**
     * 查询客户信息表列表
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
     * 导出客户信息表列表
     */
    @RequiresPermissions("customer:main:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YmswCustomer ymswCustomer)
    {
        List<YmswCustomer> list = ymswCustomerService.selectYmswCustomerList(ymswCustomer);
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
    public AjaxResult addSave(YmswCustomer ymswCustomer)
    {

        return ymswCustomerService.insertYmswCustomer(ymswCustomer);
    }

    /**
     * 修改客户信息表
     */
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        YmswCustomer ymswCustomer = ymswCustomerService.selectYmswCustomerById(customerId);
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
    public AjaxResult editSave(YmswCustomer ymswCustomer)
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
}