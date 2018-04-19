package com.wxmp.gather.mapper;

import com.wxmp.gather.domain.RentSourceUser;

import java.util.List;

public interface RentSourceUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RentSourceUser record);

    int insertSelective(RentSourceUser record);

    RentSourceUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RentSourceUser record);

    int updateByPrimaryKey(RentSourceUser record);

    List<RentSourceUser> selectByManagerId(String userId);

    List<RentSourceUser> selectUserBySourceId(String sourceId);

    void deleteBySourceId(String sourceId);
}