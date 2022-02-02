package com.kuang.dto;

import com.kuang.pojo.ApiBaseEntity;
import com.kuang.pojo.ApiParamsEntity;

import java.util.List;

public class ApiHistoryEntity extends ApiBaseEntity {

    public void setData(ApiBaseEntity entity){
        setId(entity.getId());
        setApiDesc(entity.getApiDesc());
        setApiUrl(entity.getApiUrl());
        setApiRequestMethods(entity.getApiRequestMethods());
    }

    private List<ApiParamsEntity> apiHistoryParamsEntityList;

    public List<ApiParamsEntity> getApiHistoryParamsEntityList() {
        return apiHistoryParamsEntityList;
    }

    public void setApiHistoryParamsEntityList(List<ApiParamsEntity> apiHistoryParamsEntityList) {
        this.apiHistoryParamsEntityList = apiHistoryParamsEntityList;
    }
}
