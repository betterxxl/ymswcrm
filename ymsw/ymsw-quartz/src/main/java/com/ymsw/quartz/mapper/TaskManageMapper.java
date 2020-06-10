package com.ymsw.quartz.mapper;

import com.ymsw.quartz.domain.TaskManage;
import java.util.List;

/**
 * 定时提醒Mapper接口
 * 
 * @author ymsw
 * @date 2020-06-10
 */
public interface TaskManageMapper 
{
    /**
     * 查询定时提醒
     * 
     * @param taskId 定时提醒ID
     * @return 定时提醒
     */
    public TaskManage selectTaskManageById(Long taskId);

    /**
     * 查询定时提醒列表
     * 
     * @param taskManage 定时提醒
     * @return 定时提醒集合
     */
    public List<TaskManage> selectTaskManageList(TaskManage taskManage);

    /**
     * 新增定时提醒
     * 
     * @param taskManage 定时提醒
     * @return 结果
     */
    public int insertTaskManage(TaskManage taskManage);

    /**
     * 修改定时提醒
     * 
     * @param taskManage 定时提醒
     * @return 结果
     */
    public int updateTaskManage(TaskManage taskManage);

    /**
     * 删除定时提醒
     * 
     * @param taskId 定时提醒ID
     * @return 结果
     */
    public int deleteTaskManageById(Long taskId);

    /**
     * 批量删除定时提醒
     * 
     * @param taskIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTaskManageByIds(String[] taskIds);
}
