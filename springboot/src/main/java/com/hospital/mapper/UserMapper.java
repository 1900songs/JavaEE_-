package com.hospital.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hospital.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作user相关数据接口
*/
public interface UserMapper extends BaseMapper<User> {
    List<User> selectAll(User user);

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);
}