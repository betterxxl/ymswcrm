package com.ymsw.customer.vo;

import com.ymsw.system.domain.SysUser;

import java.util.List;

//抽回重分配页面用于封装提交到后台的参数
public class YmswReallocationVo {

    private List<SysUser> userList;

    private List<Long> ids;

    public List<SysUser> getUserList() {
        return userList;
    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
