package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @PostMapping("/add")
    public Result<Void> add(@RequestBody Doctor doctor) {
        doctorService.add(doctor);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteById(@PathVariable Integer id) {
        doctorService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result<Void> deleteBatch(@RequestBody List<Integer> ids) {
        doctorService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Doctor doctor) {
        doctorService.updateById(doctor);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result<Doctor> selectById(@PathVariable Integer id) {
        Doctor doctor = doctorService.selectById(id);
        return Result.success(doctor);
    }

    @GetMapping("/selectAll")
    public Result<Doctor> selectAll(Doctor doctor ) {
        List<Doctor> list = doctorService.selectAll(doctor);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result<PageInfo<Doctor>> selectPage(Doctor doctor,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Doctor> page = doctorService.selectPage(doctor, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectPage2")
    public Result<PageInfo<Doctor>> selectPage2(Doctor doctor,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Doctor> page = doctorService.selectPage2(doctor, pageNum, pageSize);
        return Result.success(page);
    }
}