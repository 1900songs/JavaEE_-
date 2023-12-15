package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.Department;

import java.util.List;

/**
 * 操作department相关数据接口
*/
public interface DepartmentMapper extends BaseMapper<Department> {
    List<Department> selectAll(Department department);
}