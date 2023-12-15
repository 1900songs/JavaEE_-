package com.hospital.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.hospital.common.enums.RoleEnum;
import com.hospital.common.enums.StatusEnum;
import com.hospital.entity.Account;
import com.hospital.entity.Record;
import com.hospital.mapper.RecordMapper;
import com.hospital.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class RecordService {

    @Resource
    private RecordMapper recordMapper;

    public void add(Record record) {
        String time = DateUtil.format(new Date(), "yyyy-MM-dd");
        record.setTime(time);
        List<Record> records = recordMapper.selectAll(record);
        if (CollectionUtil.isEmpty(records)) {
            record.setInhostpitalRecord(StatusEnum.NO.status);
            recordMapper.insert(record);
        }
    }

    public void deleteById(Integer id) {
        recordMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            recordMapper.deleteById(id);
        }
    }

    public void updateById(Record record) {
        recordMapper.updateById(record);
    }

    public Record selectById(Integer id) {
        return recordMapper.selectById(id);
    }

    public List<Record> selectAll(Record record) {
        return recordMapper.selectAll(record);
    }

    public PageInfo<Record> selectPage(Record record, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            record.setUserId(currentUser.getId());
        }
        if (RoleEnum.DOCTOR.name().equals(currentUser.getRole())) {
            record.setDoctorId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Record> list = recordMapper.selectAll(record);
        return PageInfo.of(list);
    }

}