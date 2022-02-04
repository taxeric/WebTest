package com.kuang.service;

import com.kuang.dto.ApiHistoryEntity;

import java.util.List;

@Deprecated
public interface ApiBaseService {

    List<ApiHistoryEntity> selectAllApis();
}
