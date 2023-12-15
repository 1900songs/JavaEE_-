package com.hospital.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.common.Constants;
import com.hospital.common.enums.ResultCodeEnum;
import com.hospital.common.enums.RoleEnum;
import com.hospital.entity.Account;
import com.hospital.entity.User;
import com.hospital.exception.CustomException;
import com.hospital.mapper.UserMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {


    public void add(User user) {
        User dbUser = this.baseMapper.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
        user.setRole(RoleEnum.USER.name());
        this.baseMapper.insert(user);
    }

    public void deleteById(Integer id) {
        this.baseMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.baseMapper.deleteById(id);
        }
    }
    public User selectById(Integer id) {
        return this.baseMapper.selectById(id);
    }

    public List<User> selectAll(User user) {

            LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
            queryWrapper.select(User::getId, User::getUsername, User::getPassword, User::getName,
                            User::getPhone, User::getEmail, User::getAvatar, User::getRole)
                    .eq(user.getId() != null, User::getId, user.getId())
                    .like(user.getUsername() != null, User::getUsername, "%" + user.getUsername() + "%")
                    .eq(user.getPassword() != null, User::getPassword, user.getPassword())
                    .eq(user.getName() != null, User::getName, user.getName())
                    .eq(user.getPhone() != null, User::getPhone, user.getPhone())
                    .eq(user.getEmail() != null, User::getEmail, user.getEmail())
                    .eq(user.getAvatar() != null, User::getAvatar, user.getAvatar())
                    .eq(user.getRole() != null, User::getRole, user.getRole());
            return this.baseMapper.selectList(queryWrapper);
    }
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = selectAll(user);
        return PageInfo.of(list);
    }
    public Account login(Account account) {
        Account dbUser = this.baseMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
        // 生成token
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public void register(Account account) {
        User user = new User();
        BeanUtils.copyProperties(account, user);
        add(user);
    }

    public void updatePassword(Account account) {
        User dbUser = this.baseMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
        dbUser.setPassword(account.getNewPassword());
        this.baseMapper.updateById(dbUser);
    }

}