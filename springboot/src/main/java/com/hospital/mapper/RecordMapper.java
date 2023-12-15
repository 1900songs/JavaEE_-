package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.Record;

import java.util.List;

/**
 * 操作record相关数据接口
*/
public interface RecordMapper extends BaseMapper<Record> {
    List<Record> selectAll(Record record);
}