package com.wxmp.gather.mapper;

import com.wxmp.gather.domain.RentSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RentSourceMapper {
    int deleteByPrimaryKey(String id);

    int insert(RentSource record);

    int insertSelective(RentSource record);

    RentSource selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RentSource record);

    int updateByPrimaryKey(RentSource record);

    List<RentSource> selectByIds(@Param("ids") List ids,@Param("name") String name);
}