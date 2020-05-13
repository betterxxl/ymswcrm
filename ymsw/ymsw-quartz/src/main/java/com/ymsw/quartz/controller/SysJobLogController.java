package com.ymsw.quartz.controller;

import com.ymsw.common.annotation.Log;
import com.ymsw.common.core.controller.BaseController;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.core.page.TableDataInfo;
import com.ymsw.common.enums.BusinessType;
import com.ymsw.common.utils.poi.ExcelUtil;
import com.ymsw.quartz.domain.SysJobLog;
import com.ymsw.quartz.service.ISysJobLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 调度日志操作处理
 * 
 * @author ymsw
 */
@Controller
@RequestMapping("/monitor/jobLog")
public class SysJobLogController extends BaseController
{
    private String prefix = "monitor/templates.monitor.job";

    @Autowired
    private ISysJobLogService jobLogService;

    @RequiresPermissions("monitor:templates.monitor.job:view")
    @GetMapping()
    public String jobLog()
    {
        return prefix + "/jobLog";
    }

    @RequiresPermissions("monitor:templates.monitor.job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJobLog jobLog)
    {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        return getDataTable(list);
    }

    @Log(title = "调度日志", businessType = BusinessType.EXPORT)
    @RequiresPermissions("monitor:templates.monitor.job:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysJobLog jobLog)
    {
        List<SysJobLog> list = jobLogService.selectJobLogList(jobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        return util.exportExcel(list, "调度日志");
    }

    @Log(title = "调度日志", businessType = BusinessType.DELETE)
    @RequiresPermissions("monitor:templates.monitor.job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(jobLogService.deleteJobLogByIds(ids));
    }

    @RequiresPermissions("monitor:templates.monitor.job:detail")
    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap)
    {
        mmap.put("name", "jobLog");
        mmap.put("jobLog", jobLogService.selectJobLogById(jobLogId));
        return prefix + "/detail";
    }

    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @RequiresPermissions("monitor:templates.monitor.job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean()
    {
        jobLogService.cleanJobLog();
        return success();
    }
}
