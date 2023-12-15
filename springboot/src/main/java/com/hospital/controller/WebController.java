package com.hospital.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.hospital.common.Result;
import com.hospital.common.enums.ResultCodeEnum;
import com.hospital.common.enums.RoleEnum;
import com.hospital.entity.Account;
import com.hospital.service.AdminService;
import com.hospital.service.DoctorService;
import com.hospital.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class WebController {

    @Resource
    private AdminService adminService;
    @Resource
    private DoctorService doctorService;
    @Resource
    private UserService userService;

    @GetMapping("/")
    public Result<String> hello() {
        return Result.success("访问成功");
    }


    @PostMapping("/login")
    public Result<Void> login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername()) || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            return Result.success(adminService.login(account));
        }
        if (RoleEnum.DOCTOR.name().equals(account.getRole())) {
            return Result.success(doctorService.login(account));
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            return Result.success(userService.login(account));
        }
        return Result.success();
    }


    @PostMapping("/register")
    public Result<Void> register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.register(account);
        }
        return Result.success();
    }

    @PutMapping("/updatePassword")
    public Result<Void> updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername()) || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        if (RoleEnum.DOCTOR.name().equals(account.getRole())) {
            doctorService.updatePassword(account);
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.updatePassword(account);
        }
        return Result.success();
    }

}
