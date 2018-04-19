package com.wxmp.gather.service;

import com.wxmp.gather.domain.RentSource;
import com.wxmp.gather.domain.RentSourceUser;

import java.util.List;

/**
 * Created by shenwenjun on 2018/4/13.
 */
public interface SourceService {
    public List<RentSource> getManagerSource(String userId, String name);

    public void addSource(RentSource source,String userId) throws Exception;

    public RentSource getSource(String sourceId);
    public void delSource(String sourceId);

    public List<RentSourceUser> getSourceUser(String sourceId);
}
