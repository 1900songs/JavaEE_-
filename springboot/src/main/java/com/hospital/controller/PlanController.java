package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Plan;
import com.hospital.service.PlanService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/plan")
public class PlanController {

    @Resource
    private PlanService planService;

    @PostMapping("/add")
    public Result add(@RequestBody Plan plan) {
        planService.add(plan);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        planService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        planService.deleteBatch(ids);
        return Result.success();
    }

    @PutMapping("/update")
    public Result updateById(@RequestBody Plan plan) {
        planService.updateById(plan);
        return Result.success();
    }


    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Plan plan = planService.selectById(id);
        return Result.success(plan);
    }

    @GetMapping("/selectAll")
    public Result selectAll(Plan plan ) {
        List<Plan> list = planService.selectAll(plan);
        return Result.success(list);
    }


    @GetMapping("/selectPage")
    public Result selectPage(Plan plan,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Plan> page = planService.selectPage(plan, pageNum, pageSize);
        return Result.success(page);
    }

}