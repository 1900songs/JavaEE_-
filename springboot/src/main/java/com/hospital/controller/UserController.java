package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户前端操作接口
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/add")
    public Result<Void> add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result<Void> deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result<User> selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    @GetMapping("/selectAll")
    public Result<List<User>> selectAll(User user ) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result<PageInfo<User>> selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }

}