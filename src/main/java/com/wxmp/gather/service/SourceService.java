package com.wxmp.gather.service;

import com.wxmp.gather.domain.RentSource;

import java.util.List;

/**
 * Created by shenwenjun on 2018/4/13.
 */
public interface SourceService {
    public List<RentSource> getManagerSource(String userId, String name);

    public void addSource(RentSource source,String userId) throws Exception;
}
