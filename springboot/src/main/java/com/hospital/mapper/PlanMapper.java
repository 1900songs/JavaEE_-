package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.Plan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作plan相关数据接口
*/
public interface PlanMapper extends BaseMapper<Plan> {


    List<Plan> selectAll(Plan plan);

    @Select("select * from plan where doctor_id = #{doctorId} and week = #{week}")
    Plan selectByDoctorIdAndWeek(@Param("doctorId") Integer doctorId, @Param("week") String week);
}