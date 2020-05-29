package com.ymsw.order.controller;

import java.util.List;

import com.ymsw.common.core.domain.BaseEntity;
import com.ymsw.framework.config.AccessPhoneConfig;
import com.ymsw.framework.util.ShiroUtils;
import com.ymsw.framework.web.domain.server.Sys;
import com.ymsw.system.domain.SysDept;
import com.ymsw.system.domain.SysUser;
import com.ymsw.system.service.ISysDeptService;
import com.ymsw.system.service.ISysUserService;
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
import com.ymsw.order.domain.YmswOrder;
import com.ymsw.order.service.IYmswOrderService;
import com.ymsw.common.core.controller.BaseController;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.utils.poi.ExcelUtil;
import com.ymsw.common.core.page.TableDataInfo;

/**
 * 订单信息表Controller
 * 
 * @author ymsw
 * @date 2020-05-22
 */
@Controller
@RequestMapping("/order/main")
public class YmswOrderController extends BaseController
{
    private String prefix = "order/main";

    @Autowired
    private IYmswOrderService ymswOrderService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    AccessPhoneConfig accessPhoneConfig;

    @RequiresPermissions("order:main:view")
    @GetMapping()
    public String main(ModelMap mmap)
    {
        BaseEntity baseEntity = new BaseEntity();
        List<SysUser> sysUsers = sysUserService.selectUsers(baseEntity);//根据数据范围查询所有在职员工列表，除了超级管理员
        List<SysDept> sysDepts = sysDeptService.selectDepts(baseEntity);//根据数据范围查询部门列表
        mmap.put("sysUsers", sysUsers);
        mmap.put("sysDepts", sysDepts);
        return prefix + "/main";
    }

    /**
     * 查询订单信息表列表
     */
    @RequiresPermissions("order:main:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YmswOrder ymswOrder)
    {
        startPage();
        List<YmswOrder> list = ymswOrderService.selectYmswOrderList(ymswOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单信息表列表
     */
    @RequiresPermissions("order:main:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YmswOrder ymswOrder)
    {
        List<YmswOrder> list = ymswOrderService.selectYmswOrderList(ymswOrder);
        ExcelUtil<YmswOrder> util = new ExcelUtil<YmswOrder>(YmswOrder.class);
        return util.exportExcel(list, "main");
    }

    /**
     * 新增订单信息表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单信息表
     */
    @RequiresPermissions("order:main:add")
    @Log(title = "订单信息表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YmswOrder ymswOrder)
    {
        return toAjax(ymswOrderService.insertYmswOrder(ymswOrder));
    }

    /**
     * 修改订单信息表
     */
    @GetMapping("/edit/{orderId}")
    public String edit(@PathVariable("orderId") Long orderId, ModelMap mmap)
    {
        YmswOrder ymswOrder = ymswOrderService.selectYmswOrderById(orderId);//通过订单id查询该订单的详情
        List<SysUser> sysUsers = sysUserService.selectUsersForIncomingData(accessPhoneConfig.getUserIds());//查询可分配客户的业务经理列表
        mmap.put("ymswOrder", ymswOrder);
        mmap.put("sysUsers", sysUsers);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单信息表
     */
    @RequiresPermissions("order:main:edit")
    @Log(title = "订单信息表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YmswOrder ymswOrder)
    {
        return toAjax(ymswOrderService.updateYmswOrder(ymswOrder));
    }

    /**
     * 删除订单信息表
     */
    @RequiresPermissions("order:main:remove")
    @Log(title = "订单信息表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ymswOrderService.deleteYmswOrderByIds(ids));
    }
}
