package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.Reserve;

import java.util.List;

/**
 * 操作reserve相关数据接口
*/
public interface ReserveMapper extends BaseMapper<Reserve> {
    List<Reserve> selectAll(Reserve reserve);
}