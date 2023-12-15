package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Reserve;
import com.hospital.service.ReserveService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Resource
    private ReserveService reserveService;

    @PostMapping("/add")
    public Result<Void> add(@RequestBody Reserve reserve) {
        reserveService.add(reserve);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteById(@PathVariable Integer id) {
        reserveService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result<Void> deleteBatch(@RequestBody List<Integer> ids) {
        reserveService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Reserve reserve) {
        reserveService.updateById(reserve);
        return Result.success();
    }

    @GetMapping("/selectById/{id}")
    public Result<Reserve> selectById(@PathVariable Integer id) {
        Reserve reserve = reserveService.selectById(id);
        return Result.success(reserve);
    }

    @GetMapping("/selectAll")
    public Result<List<Reserve>> selectAll(Reserve reserve ) {
        List<Reserve> list = reserveService.selectAll(reserve);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result<PageInfo<Reserve>> selectPage(Reserve reserve,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Reserve> page = reserveService.selectPage(reserve, pageNum, pageSize);
        return Result.success(page);
    }

}