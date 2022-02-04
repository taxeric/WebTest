package com.kuang.service;

import com.kuang.pojo.VersionControlEntity;

public interface VersionControlService {

    VersionControlEntity getAppInfoVersionById(String versionId);
    int insertAppVersion(VersionControlEntity entity);
    int updateAppVersion(VersionControlEntity entity);
    int deleteAppVersion(String versionControlId);
}
