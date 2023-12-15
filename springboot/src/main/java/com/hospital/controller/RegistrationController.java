package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Registration;
import com.hospital.service.RegistrationService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/registration")
public class RegistrationController {

    @Resource
    private RegistrationService registrationService;

    @PostMapping("/add")
    public Result<Void> add(@RequestBody Registration registration) {
        registrationService.add(registration);
        return Result.success();
    }


    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteById(@PathVariable Integer id) {
        registrationService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result<Void> deleteBatch(@RequestBody List<Integer> ids) {
        registrationService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Registration registration) {
        registrationService.updateById(registration);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result<Registration> selectById(@PathVariable Integer id) {
        Registration registration = registrationService.selectById(id);
        return Result.success(registration);
    }

    @GetMapping("/selectAll")
    public Result<List<Registration>> selectAll(Registration registration ) {
        List<Registration> list = registrationService.selectAll(registration);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result<PageInfo<Registration>> selectPage(Registration registration,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Registration> page = registrationService.selectPage(registration, pageNum, pageSize);
        return Result.success(page);
    }

}