package com.hospital.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.hospital.common.Constants;
import com.hospital.common.enums.ResultCodeEnum;
import com.hospital.common.enums.RoleEnum;
import com.hospital.entity.*;
import com.hospital.exception.CustomException;
import com.hospital.mapper.DepartmentMapper;
import com.hospital.mapper.DoctorMapper;
import com.hospital.mapper.PlanMapper;
import com.hospital.mapper.ReserveMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Service
public class DoctorService {

    @Resource
    private DoctorMapper doctorMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private ReserveMapper reserveMapper;
    @Resource
    private PlanMapper planMapper;


    public void add(Doctor doctor) {
        Doctor dbDoctor = doctorMapper.selectByUsername(doctor.getUsername());
        if (ObjectUtil.isNotNull(dbDoctor)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(doctor.getPassword())) {
            doctor.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(doctor.getName())) {
            doctor.setName(doctor.getUsername());
        }
        doctor.setRole(RoleEnum.DOCTOR.name());
        doctorMapper.insert(doctor);
    }


    public void deleteById(Integer id) {
        doctorMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            doctorMapper.deleteById(id);
        }
    }


    public void updateById(Doctor doctor) {
        doctorMapper.updateById(doctor);
    }

    public Doctor selectById(Integer id) {
        return doctorMapper.selectById(id);
    }

    public List<Doctor> selectAll(Doctor doctor) {
        return doctorMapper.selectAll(doctor);
    }


    public PageInfo<Doctor> selectPage(Doctor doctor, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Doctor> list = doctorMapper.selectAll(doctor);
        return PageInfo.of(list);
    }

    public PageInfo<Doctor> selectPage2(Doctor doctor, Integer pageNum, Integer pageSize) {
        String today = DateUtil.format(new Date(), "yyyy-MM-dd");

        String week = getTodayWeek();
        doctor.setWeek(week);
        PageHelper.startPage(pageNum, pageSize);
        List<Doctor> list = doctorMapper.selectAll(doctor);

        for (Doctor dbDoctor : list) {

            Reserve reserve = new Reserve();
            reserve.setDoctorId(dbDoctor.getId());
            reserve.setTime(today);
            List<Reserve> reserves = reserveMapper.selectAll(reserve);
            Plan plan = planMapper.selectByDoctorIdAndWeek(dbDoctor.getId(), week);

            dbDoctor.setNum(plan.getNum() - reserves.size());
        }
        return PageInfo.of(list);
    }


    private String getTodayWeek() {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA);
    }


    public Account login(Account account) {
        Doctor dbDoctor = doctorMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbDoctor)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbDoctor.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbDoctor.getId() + "-" + RoleEnum.DOCTOR.name();
        String token = TokenUtils.createToken(tokenData, dbDoctor.getPassword());
        dbDoctor.setToken(token);
        if (ObjectUtil.isNotEmpty(dbDoctor.getDepartmentId())) {
            Department department = departmentMapper.selectById(dbDoctor.getDepartmentId());
            if (ObjectUtil.isNotEmpty(department)) {
                dbDoctor.setDepartmentName(department.getName());
            }
        }
        return dbDoctor;
    }

    public void updatePassword(Account account) {
        Doctor dbDoctor = doctorMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbDoctor)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbDoctor.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbDoctor.setPassword(account.getNewPassword());
        doctorMapper.updateById(dbDoctor);
    }

}