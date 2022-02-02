package com.kuang.service;

import com.kuang.dto.ApiHistoryEntity;
import com.kuang.pojo.ApiBaseEntity;
import com.kuang.pojo.ApiParamsEntity;

import java.util.List;

public interface ApiHistoryService {

    List<ApiHistoryEntity> selectAllApis();

    int insertApi(ApiBaseEntity entity);

    void insertApiParams(List<ApiParamsEntity> entity);

    int insertParam(ApiParamsEntity entity);

    List<ApiParamsEntity> selectListByUrlId(Integer id);
}
