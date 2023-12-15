package com.hospital.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.common.enums.ResultCodeEnum;
import com.hospital.common.enums.RoleEnum;
import com.hospital.entity.Account;
import com.hospital.entity.Plan;
import com.hospital.exception.CustomException;
import com.hospital.mapper.PlanMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 排班信息表业务处理
 **/
@Service
public class PlanService extends ServiceImpl<PlanMapper,Plan> {


    public void add(Plan plan) {

        Plan dbPlan = this.baseMapper.selectByDoctorIdAndWeek(plan.getDoctorId(), plan.getWeek());
        if (ObjectUtil.isNotEmpty(dbPlan)) {
            throw new CustomException(ResultCodeEnum.PLAN_EXIST_ERROR);
        }
        this.baseMapper.insert(plan);
    }


    public void deleteById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.baseMapper.deleteById(id);
        }
    }


    public Plan selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    public List<Plan> selectAll(Plan plan) {

        return this.baseMapper.selectAll(plan);
    }

    public PageInfo<Plan> selectPage(Plan plan, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.DOCTOR.name().equals(currentUser.getRole())) {
            plan.setDoctorId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Plan> list = baseMapper.selectAll(plan);
        return PageInfo.of(list);
    }

}