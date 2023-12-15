package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Department;
import com.hospital.service.DepartmentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    private DepartmentService departmentService;


    @PostMapping("/add")
    public Result<Void> add(@RequestBody Department department) {
        departmentService.add(department);
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    public Result<Void>  deleteById(@PathVariable Integer id) {
        departmentService.deleteById(id);
        return Result.success();
    }


    @DeleteMapping("/delete/batch")
    public Result<Void>  deleteBatch(@RequestBody List<Integer> ids) {
        departmentService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void>  updateById(@RequestBody Department department) {
        departmentService.updateById(department);
        return Result.success();
    }


    @GetMapping("/selectById/{id}")
    public Result<Department>  selectById(@PathVariable Integer id) {
        Department department = departmentService.selectById(id);
        return Result.success(department);
    }

    @GetMapping("/selectAll")
    public Result<List<Department>> selectAll(Department department ) {
        List<Department> list = departmentService.selectAll(department);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result<PageInfo<Department>> selectPage(Department department,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Department> page = departmentService.selectPage(department, pageNum, pageSize);
        return Result.success(page);
    }

}