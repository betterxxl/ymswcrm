package com.ymsw.customer.service.impl;

import com.ymsw.common.annotation.DataScope;
import com.ymsw.common.core.domain.AjaxResult;
import com.ymsw.common.core.text.Convert;
import com.ymsw.common.utils.DateUtils;
import com.ymsw.common.utils.StringUtils;
import com.ymsw.customer.domain.YmswCollectionPool;
import com.ymsw.customer.domain.YmswCustomer;
import com.ymsw.customer.mapper.YmswCollectionPoolMapper;
import com.ymsw.customer.mapper.YmswCustomerMapper;
import com.ymsw.customer.service.IYmswCollectionPoolService;
import com.ymsw.framework.util.ShiroUtils;
import com.ymsw.system.domain.SysDictData;
import com.ymsw.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 收藏夹-公共池Service业务层处理
 *
 * @author ymsw
 * @date 2020-05-18
 */
@Service
public class YmswCollectionPoolServiceImpl implements IYmswCollectionPoolService {
    @Autowired
    private YmswCollectionPoolMapper ymswCollectionPoolMapper;
    @Autowired
    private YmswCustomerMapper ymswCustomerMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询收藏夹-公共池
     *
     * @param cpId 收藏夹-公共池ID
     * @return 收藏夹-公共池
     */
    @Override
    public YmswCollectionPool selectYmswCollectionPoolById(Long cpId) {
        return ymswCollectionPoolMapper.selectYmswCollectionPoolById(cpId);
    }

    /**
     * 查询收藏夹列表(根据数据范围查询)
     *
     * @param ymswCustomer 查询条件
     * @return 收藏夹集合
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<YmswCustomer> selectYmswCollectionPoolList(YmswCustomer ymswCustomer) {
        return ymswCollectionPoolMapper.selectYmswCollectionPoolList(ymswCustomer);
    }

    /**
     * 新增收藏夹-公共池
     *
     * @param ymswCollectionPool 收藏夹-公共池
     * @return 结果
     */
    @Override
    public int insertYmswCollectionPool(YmswCollectionPool ymswCollectionPool) {
        return ymswCollectionPoolMapper.insertYmswCollectionPool(ymswCollectionPool);
    }

    /**
     * 修改收藏夹-公共池
     *
     * @param ymswCollectionPool 收藏夹-公共池
     * @return 结果
     */
    @Override
    public int updateYmswCollectionPool(YmswCollectionPool ymswCollectionPool) {
        return ymswCollectionPoolMapper.updateYmswCollectionPool(ymswCollectionPool);
    }

    /**
     * 删除收藏夹-公共池对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteYmswCollectionPoolByIds(String ids) {
        return ymswCollectionPoolMapper.deleteYmswCollectionPoolByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收藏夹-公共池信息
     *
     * @param cpId 收藏夹-公共池ID
     * @return 结果
     */
    @Override
    public int deleteYmswCollectionPoolById(Long cpId) {
        return ymswCollectionPoolMapper.deleteYmswCollectionPoolById(cpId);
    }

    @Override
    @Transactional
    public AjaxResult addToCollectionPool(String ids, String type) {
        List<String> customerIds = Arrays.asList(ids.split(","));
        Long userId = ShiroUtils.getUserId();//当前userId
        List<String> addids = new ArrayList<>();    //需要到公共池收藏夹表的ids
        StringBuffer msg = new StringBuffer();  //返回的消息
        int errCollectCount = 0;
        int errPoolCount = 0;
        for (String customerId : customerIds) {
            if (isInCollectionPool(Long.valueOf(customerId),"1")){//是否在收藏夹，如果在，就errCollectCount++
                errCollectCount ++;
            }else if (isInCollectionPool(Long.valueOf(customerId),"2")){//是否在公共池，如果在就errPoolCount++
                errPoolCount ++;
            }else {
                addids.add(customerId); //如果既不在收藏夹，也不在公共池，就add到addids，准备添加到公共池收藏夹表
            }
        }
        if (StringUtils.isNotEmpty(addids)){
            if ("1".equals(type)) {  //批量添加到收藏夹
                SysDictData sysDictData = new SysDictData();
                sysDictData.setDictType("ymsw_config");
                sysDictData.setDictLabel("collection_count");
                List<SysDictData> sysDictDataList = sysDictDataMapper.selectDictDataList(sysDictData);//查询设置的允许收藏的条数
                if (StringUtils.isNotEmpty(sysDictDataList)) {
                    String collectionCount = sysDictDataList.get(0).getDictValue(); //设置的允许收藏的条数
                    int count = ymswCollectionPoolMapper.selectCountByUserId(userId);//查询该用户已经收藏的条数
                    if (Integer.valueOf(collectionCount) <= count) { //如果已经收藏的条数大于等于允许收藏的条数，就不能收藏。
                        return AjaxResult.error("收藏的客户数量不能超过" + collectionCount + "条！");
                    } else if (count + addids.size() > Integer.valueOf(collectionCount)) { //如果已经收藏的条数+将要收藏的条数大于允许收藏的条数，就不能收藏。
                        return AjaxResult.error("还可收藏" + (Integer.valueOf(collectionCount) - count) + "条！");
                    }
                }
            } else if ("2".equals(type)) {    //批量添加到公共池
                ymswCustomerMapper.updateUseridToNull(addids);//批量修改客户的归属顾问为空
            }
            ymswCollectionPoolMapper.batchInsertYmswCollectionPool(getAddList(addids,type,userId));    //批量添加到收藏夹公共池表
        }
        if (errCollectCount > 0){
            msg.append(errCollectCount+"条已在收藏夹，");
        }
        if (errPoolCount > 0){
            msg.append(errPoolCount+"条已在公共池，");
        }
        if (StringUtils.isNotEmpty(msg.toString())){
            if ("1".equals(type)){
                msg.append("不可加入收藏夹！");
            }else if ("2".equals(type)){
                msg.append("不可放入公共池！");
            }
        }
        msg.append(addids.size()+"条操作成功！");
        return AjaxResult.success(msg.toString());
    }

    //    返回需要批量添加到收藏夹公共池的数据集合
    private ArrayList<YmswCollectionPool> getAddList(List<String> customerIds, String type, Long userId) {
        ArrayList<YmswCollectionPool> list = new ArrayList<>();
        Date addTime = DateUtils.getNowDate();//当前时间
        for (String customerId : customerIds) {
            YmswCollectionPool ymswCollectionPool = new YmswCollectionPool();
            ymswCollectionPool.setCustomerId(Long.valueOf(customerId));//设置客户id
            ymswCollectionPool.setAddTime(addTime);//设置添加时间
            ymswCollectionPool.setCpType(type);//设置类型为收藏夹
            ymswCollectionPool.setOperUserId(userId);//设置操作人
            if ("1".equals(type)) {
                ymswCollectionPool.setUserId(userId);//如果添加到收藏夹就设置收藏人
            }
            list.add(ymswCollectionPool);
        }
        return list;
    }

    //  通过customerId和类型，查询是否已经在收藏夹公共池表
    private boolean isInCollectionPool(Long customerId,String cpType){
        int i = ymswCollectionPoolMapper.selectIsInCollectionPool(customerId, cpType);
        return i > 0 ? true : false;
    }

    /**
     * 查询公共池列表(不做数据范围限制，查询所有公共池数据)
     * @param ymswCustomer 查询条件
     * @return 结果
     */
    @Override
    public List<YmswCustomer> selectYmswPoolList(YmswCustomer ymswCustomer) {
        return ymswCollectionPoolMapper.selectYmswPoolList(ymswCustomer);
    }
}
