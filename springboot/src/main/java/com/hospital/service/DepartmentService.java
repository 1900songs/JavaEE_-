package com.hospital.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.entity.Admin;
import com.hospital.entity.Department;
import com.hospital.mapper.AdminMapper;
import com.hospital.mapper.DepartmentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DepartmentService extends ServiceImpl<DepartmentMapper, Department> {

    @Resource
    private DepartmentMapper departmentMapper;

    public void add(Department department) {
        departmentMapper.insert(department);
    }

    public void deleteById(Integer id) {
        departmentMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            departmentMapper.deleteById(id);
        }
    }

    public Department selectById(Integer id) {
        return departmentMapper.selectById(id);
    }

    public List<Department> selectAll(Department department) {
           return  departmentMapper.selectAll(department);
    }

    public PageInfo<Department> selectPage(Department department, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Department> list = selectAll(department);
        return PageInfo.of(list);
    }

}