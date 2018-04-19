package com.wxmp.gather.service.impl;

import com.wxmp.core.util.StringUtil;
import com.wxmp.gather.constant.GatherMessage;
import com.wxmp.gather.domain.RentSource;
import com.wxmp.gather.domain.RentSourceUser;
import com.wxmp.gather.mapper.RentSourceMapper;
import com.wxmp.gather.mapper.RentSourceUserMapper;
import com.wxmp.gather.service.SourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shenwenjun on 2018/4/13.
 */
@Service("sourceService")
public class SourceServiceImpl implements SourceService{
    @Resource
    RentSourceUserMapper rentSourceUserMapper;
    @Resource
    RentSourceMapper rentSourceMapper;

    @Override
    public List<RentSource> getManagerSource(String userId, String name) {
        List<RentSourceUser> rsu = rentSourceUserMapper.selectByManagerId(userId);
        if (rsu.size() > 0) {
            List<String> sourceIds = new ArrayList<>(rsu.size());
            for (RentSourceUser r : rsu) {
                sourceIds.add(r.getSourceId());
            }
            List<RentSource> rss = rentSourceMapper.selectByIds(sourceIds, name);
            return rss;
        }
        return null;
    }

    @Override
    public void addSource(RentSource source, String userId) throws Exception {
        List<RentSourceUser> rsu = rentSourceUserMapper.selectByManagerId(userId);
        if (rsu.size() == 20)
            throw new Exception(String.valueOf(GatherMessage.SOURCE_END_LIMIT));
        // 生成二维码

        rentSourceMapper.insertSelective(source);
        RentSourceUser rs = new RentSourceUser();
        rs.setSourceId(source.getId());
        rs.setUserId(userId);
        rs.setType(2);
        rentSourceUserMapper.insertSelective(rs);
    }

    @Override
    public RentSource getSource(String sourceId) {
        return rentSourceMapper.selectByPrimaryKey(sourceId);
    }

    @Override
    public void delSource(String sourceId) {
        rentSourceMapper.deleteByPrimaryKey(sourceId);
        rentSourceUserMapper.deleteBySourceId(sourceId);
    }

    @Override
    public List<RentSourceUser> getSourceUser(String sourceId) {
        return rentSourceUserMapper.selectUserBySourceId(sourceId);
    }
}
