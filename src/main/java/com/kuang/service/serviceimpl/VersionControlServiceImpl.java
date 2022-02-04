package com.kuang.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kuang.mapper.VersionControlMapper;
import com.kuang.pojo.VersionControlEntity;
import com.kuang.service.VersionControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionControlServiceImpl implements VersionControlService {

    @Autowired
    private VersionControlMapper versionControlMapper;

    @Override
    public VersionControlEntity getAppInfoVersionById(String versionId) {
        return versionControlMapper.selectByVersionId(versionId);
    }

    @Override
    public int insertAppVersion(VersionControlEntity entity) {
        return versionControlMapper.insert(entity);
    }

    @Override
    public int updateAppVersion(VersionControlEntity entity) {
        return versionControlMapper.updateById(entity);
    }

    @Override
    public int deleteAppVersion(String versionControlId) {
        return -1;
    }
}
