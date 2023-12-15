package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.Registration;

import java.util.List;

/**
 * 操作registration相关数据接口
*/
public interface RegistrationMapper extends BaseMapper<Registration> {
    List<Registration> selectAll(Registration registration);
}