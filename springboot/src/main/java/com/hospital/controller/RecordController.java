package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Record;
import com.hospital.service.RecordService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {

    @Resource
    private RecordService recordService;

    @PostMapping("/add")
    public Result<Void> add(@RequestBody Record record) {
        recordService.add(record);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteById(@PathVariable Integer id) {
        recordService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result<Void> deleteBatch(@RequestBody List<Integer> ids) {
        recordService.deleteBatch(ids);
        return Result.success();
    }


    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Record record) {
        recordService.updateById(record);
        return Result.success();
    }


    @GetMapping("/selectById/{id}")
    public Result<Record> selectById(@PathVariable Integer id) {
        Record record = recordService.selectById(id);
        return Result.success(record);
    }

    @GetMapping("/selectAll")
    public Result<List<Record>> selectAll(Record record ) {
        List<Record> list = recordService.selectAll(record);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result<PageInfo<Record>> selectPage(Record record,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Record> page = recordService.selectPage(record, pageNum, pageSize);
        return Result.success(page);
    }

}