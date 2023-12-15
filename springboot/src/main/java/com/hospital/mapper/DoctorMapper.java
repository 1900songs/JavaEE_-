package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.Doctor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作doctor相关数据接口
*/
public interface DoctorMapper extends BaseMapper<Doctor> {

    @Select("select * from doctor where username = #{username}")
    Doctor selectByUsername(String username);

    List<Doctor> selectAll(Doctor doctor);
}