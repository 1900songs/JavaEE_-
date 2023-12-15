package com.hospital.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.entity.Account;
import com.hospital.entity.Admin;
import com.hospital.entity.Notice;
import com.hospital.mapper.AdminMapper;
import com.hospital.mapper.NoticeMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {


    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        Account currentUser = TokenUtils.getCurrentUser();
        notice.setUser(currentUser.getUsername());
        this.baseMapper.insert(notice);
    }

    public void deleteById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.baseMapper.deleteById(id);
        }
    }


    public Notice selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    public List<Notice> selectAll(Notice notice) {

        return baseMapper.selectAll(notice);
    }

    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = this.selectAll(notice);
        return PageInfo.of(list);
    }

}