package com.ymsw.quota.controller;

import java.util.*;

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
import com.ymsw.quota.domain.QuotaManager;
import com.ymsw.quota.service.IQuotaManagerService;
import com.ymsw.common.core.controller.BaseController;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.utils.poi.ExcelUtil;
import com.ymsw.common.core.page.TableDataInfo;

/**
 * 配额管理Controller
 * 
 * @author ymsw
 * @date 2020-06-04
 */
@Controller
@RequestMapping("/quota/main")
public class QuotaManagerController extends BaseController
{
    private String prefix = "quota/main";

    @Autowired
    private IQuotaManagerService quotaManagerService;

    @RequiresPermissions("quota:main:view")
    @GetMapping()
    public String main()
    {
        return prefix + "/main";
    }

    /**
     * 查询配额管理列表
     */
    @RequiresPermissions("quota:main:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(QuotaManager quotaManager)
    {
        startPage();
        List<QuotaManager> list = quotaManagerService.selectQuotaManagerList(quotaManager);
        return getDataTable(list);
    }

    /**
     * 导出配额管理列表
     */
    @RequiresPermissions("quota:main:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(QuotaManager quotaManager)
    {
        List<QuotaManager> list = quotaManagerService.selectQuotaManagerList(quotaManager);
        ExcelUtil<QuotaManager> util = new ExcelUtil<QuotaManager>(QuotaManager.class);
        return util.exportExcel(list, "main");
    }

    /**
     * 新增配额管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存配额管理
     */
    @RequiresPermissions("quota:main:add")
    @Log(title = "配额管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(QuotaManager quotaManager)
    {
        return toAjax(quotaManagerService.insertQuotaManager(quotaManager));
    }

    /**
     * 修改配额管理
     */
    @GetMapping("/edit/{quotaId}")
    public String edit(@PathVariable("quotaId") Long quotaId, ModelMap mmap)
    {
        QuotaManager quotaManager = quotaManagerService.selectQuotaManagerById(quotaId);
        mmap.put("quotaManager", quotaManager);
        return prefix + "/edit";
    }

    /**
     * 修改保存配额管理
     */
    @RequiresPermissions("quota:main:edit")
    @Log(title = "配额管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(QuotaManager quotaManager)
    {
        return toAjax(quotaManagerService.updateQuotaManager(quotaManager));
    }

    /**
     * 删除配额管理
     */
    @RequiresPermissions("quota:main:remove")
    @Log(title = "配额管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(quotaManagerService.deleteQuotaManagerByIds(ids));
    }

    /**
     * 批量修改配额状态
     */
    @RequiresPermissions("quota:main:changeStatus")
    @Log(title = "配额管理", businessType = BusinessType.UPDATE)
    @PostMapping( "/batchChangeStatus")
    @ResponseBody
    public AjaxResult batchChangeStatus(String ids, String quotaStatus)
    {
        List<String> quotaIds = Arrays.asList(ids.split(","));
        Map<String, Object> params = new HashMap<>();
        params.put("quotaIds", quotaIds);
        params.put("quotaStatus", quotaStatus);
        return toAjax(quotaManagerService.changeStatus(params));
    }

    /**
     * 批量修改配额状态
     */
    @PostMapping( "/countTotal")
    @ResponseBody
    public AjaxResult countTotal()
    {
        int total = quotaManagerService.countTotal();
        return AjaxResult.success(total);
    }
}
