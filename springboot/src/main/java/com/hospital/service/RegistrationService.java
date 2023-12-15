package com.hospital.service;

import cn.hutool.core.collection.CollectionUtil;
import com.hospital.common.enums.RoleEnum;
import com.hospital.entity.Account;
import com.hospital.entity.Registration;
import com.hospital.mapper.RegistrationMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RegistrationService {

    @Resource
    private RegistrationMapper registrationMapper;

    public void add(Registration registration) {

        List<Registration> registrations = registrationMapper.selectAll(registration);
        if (CollectionUtil.isEmpty(registrations)) {
            registrationMapper.insert(registration);
        }
    }

    public void deleteById(Integer id) {
        registrationMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            registrationMapper.deleteById(id);
        }
    }

    public void updateById(Registration registration) {
        registrationMapper.updateById(registration);
    }

    public Registration selectById(Integer id) {
        return registrationMapper.selectById(id);
    }

    public List<Registration> selectAll(Registration registration) {
        return registrationMapper.selectAll(registration);
    }


    public PageInfo<Registration> selectPage(Registration registration, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            registration.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Registration> list = registrationMapper.selectAll(registration);
        return PageInfo.of(list);
    }

}