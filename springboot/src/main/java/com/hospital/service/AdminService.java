package com.hospital.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.common.Constants;
import com.hospital.common.enums.ResultCodeEnum;
import com.hospital.common.enums.RoleEnum;
import com.hospital.entity.Account;
import com.hospital.entity.Admin;
import com.hospital.entity.User;
import com.hospital.exception.CustomException;
import com.hospital.mapper.AdminMapper;
import com.hospital.mapper.UserMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class AdminService extends ServiceImpl<AdminMapper,  Admin> {


    public void add(Admin admin) {
        Admin dbAdmin = this.baseMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }
        admin.setRole(RoleEnum.ADMIN.name());
        this.baseMapper.insert(admin);
    }


    public void deleteById(Integer id) {
        this.baseMapper.deleteById(id);
    }


    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.baseMapper.deleteById(id);
        }
    }


    public Admin selectById(Integer id) {
        return  this.baseMapper.selectById(id);
    }


    public List<Admin> selectAll(Admin admin) {
        return this.baseMapper.selectAll(admin);
    }

    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list =  selectAll(admin);
        return PageInfo.of(list);
    }

    public Account login(Account account) {
        Account dbAdmin =  this.baseMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        String tokenData = dbAdmin.getId() + "-" + RoleEnum.ADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbAdmin.getPassword());
        dbAdmin.setToken(token);
        return dbAdmin;
    }


    public void register(Account account) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(account, admin);
        add(admin);
    }

    public void updatePassword(Account account) {
        Admin dbAdmin =  this.baseMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbAdmin.setPassword(account.getNewPassword());
        this.baseMapper.updateById(dbAdmin);
    }

}