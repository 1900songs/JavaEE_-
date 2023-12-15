package com.hospital.controller;

import com.hospital.common.Result;
import com.hospital.entity.Notice;
import com.hospital.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;


    @PostMapping("/add")
    public Result<Void> add(@RequestBody Notice notice) {
        noticeService.add(notice);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result<Void> deleteById(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/delete/batch")
    public Result<Void> deleteBatch(@RequestBody List<Integer> ids) {
        noticeService.deleteBatch(ids);
        return Result.success();
    }


    @PutMapping("/update")
    public Result<Void> updateById(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }


    @GetMapping("/selectById/{id}")
    public Result<Notice> selectById(@PathVariable Integer id) {
        Notice notice = noticeService.selectById(id);
        return Result.success(notice);
    }


    @GetMapping("/selectAll")
    public Result<List<Notice>> selectAll(Notice notice ) {
        List<Notice> list = noticeService.selectAll(notice);
        return Result.success(list);
    }

    @GetMapping("/selectPage")
    public Result<PageInfo<Notice>> selectPage(Notice notice,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Notice> page = noticeService.selectPage(notice, pageNum, pageSize);
        return Result.success(page);
    }

}