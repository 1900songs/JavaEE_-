package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Admin;
import com.hospital.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    @PostMapping("/add")
    public Result<Void> add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteById(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }


    @DeleteMapping("/delete/batch")
    public Result<Void> deleteBatch(@RequestBody List<Integer> ids) {
        adminService.deleteBatch(ids);
        return Result.success();
    }


    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.success();
    }


    @GetMapping("/selectById/{id}")
    public Result<Admin> selectById(@PathVariable Integer id) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }


    @GetMapping("/selectAll")
    public Result<List<Admin>> selectAll(Admin admin ) {
        List<Admin> list = adminService.selectAll(admin);
        return Result.success(list);
    }


    @GetMapping("/selectPage")
    public Result<PageInfo<Admin>> selectPage(Admin admin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Admin> page = adminService.selectPage(admin, pageNum, pageSize);
        return Result.success(page);
    }

}