package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.Notice;
import java.util.List;

/**
 * 操作notice相关数据接口
*/
public interface NoticeMapper extends BaseMapper<Notice> {
    List<Notice> selectAll(Notice notice);
}