package com.ymsw.ranking.controller;

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
import com.ymsw.ranking.domain.YmswPerformanceRanking;
import com.ymsw.ranking.service.IYmswPerformanceRankingService;
import com.ymsw.common.core.controller.BaseController;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.utils.poi.ExcelUtil;
import com.ymsw.common.core.page.TableDataInfo;

/**
 * 业绩排行Controller
 * 
 * @author ymsw
 * @date 2020-06-22
 */
@Controller
@RequestMapping("/ranking/main")
public class YmswPerformanceRankingController extends BaseController
{
    private String prefix = "ranking/main";

    @Autowired
    private IYmswPerformanceRankingService ymswPerformanceRankingService;

    @RequiresPermissions("ranking:main:view")
    @GetMapping()
    public String main()
    {
        return prefix + "/main";
    }

    /**
     * 查询业绩排行列表
     */
    @RequiresPermissions("ranking:main:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(YmswPerformanceRanking ymswPerformanceRanking)
    {
        startPage();
        List<YmswPerformanceRanking> list = ymswPerformanceRankingService.selectYmswPerformanceRankingList(ymswPerformanceRanking);
        return getDataTable(list);
    }

    /**
     * 导出业绩排行列表
     */
    @RequiresPermissions("ranking:main:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(YmswPerformanceRanking ymswPerformanceRanking)
    {
        List<YmswPerformanceRanking> list = ymswPerformanceRankingService.selectYmswPerformanceRankingList(ymswPerformanceRanking);
        ExcelUtil<YmswPerformanceRanking> util = new ExcelUtil<YmswPerformanceRanking>(YmswPerformanceRanking.class);
        return util.exportExcel(list, "main");
    }

    /**
     * 新增业绩排行
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存业绩排行
     */
    @RequiresPermissions("ranking:main:add")
    @Log(title = "业绩排行", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(YmswPerformanceRanking ymswPerformanceRanking)
    {
        return toAjax(ymswPerformanceRankingService.insertYmswPerformanceRanking(ymswPerformanceRanking));
    }

    /**
     * 修改业绩排行
     */
    @GetMapping("/edit/{rankingId}")
    public String edit(@PathVariable("rankingId") Long rankingId, ModelMap mmap)
    {
        YmswPerformanceRanking ymswPerformanceRanking = ymswPerformanceRankingService.selectYmswPerformanceRankingById(rankingId);
        mmap.put("ymswPerformanceRanking", ymswPerformanceRanking);
        return prefix + "/edit";
    }

    /**
     * 修改保存业绩排行
     */
    @RequiresPermissions("ranking:main:edit")
    @Log(title = "业绩排行", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(YmswPerformanceRanking ymswPerformanceRanking)
    {
        return toAjax(ymswPerformanceRankingService.updateYmswPerformanceRanking(ymswPerformanceRanking));
    }

    /**
     * 删除业绩排行
     */
    @RequiresPermissions("ranking:main:remove")
    @Log(title = "业绩排行", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ymswPerformanceRankingService.deleteYmswPerformanceRankingByIds(ids));
    }
}
