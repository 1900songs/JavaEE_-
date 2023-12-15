package com.hospital.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.common.enums.CallEnum;
import com.hospital.common.enums.RoleEnum;
import com.hospital.entity.Account;
import com.hospital.entity.Reserve;
import com.hospital.mapper.ReserveMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ReserveService extends ServiceImpl<ReserveMapper, Reserve> {


    public void add(Reserve reserve) {
        reserve.setTime(DateUtil.format(new Date(), "yyyy-MM-dd"));
        reserve.setStatus(CallEnum.STATUS_NO.status);
        this.baseMapper.insert(reserve);
    }

    public void deleteById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.baseMapper.deleteById(id);
        }
    }


    public Reserve selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    public List<Reserve> selectAll(Reserve reserve) {


        return this.baseMapper.selectAll(reserve);
    }

    public PageInfo<Reserve> selectPage(Reserve reserve, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            reserve.setUserId(currentUser.getId());
        }
        if (RoleEnum.DOCTOR.name().equals(currentUser.getRole())) {
            reserve.setDoctorId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Reserve> list = this.selectAll(reserve);
        return PageInfo.of(list);
    }

}