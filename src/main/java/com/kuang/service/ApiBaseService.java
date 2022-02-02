package com.kuang.service;

import com.kuang.dto.ApiHistoryEntity;

import java.util.List;

public interface ApiBaseService {

    List<ApiHistoryEntity> selectAllApis();
}
