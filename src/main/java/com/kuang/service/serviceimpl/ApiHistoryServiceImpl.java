package com.kuang.service.serviceimpl;

import com.kuang.dto.ApiHistoryEntity;
import com.kuang.mapper.ApiBaseMapper;
import com.kuang.mapper.ApiParamsMapper;
import com.kuang.pojo.ApiBaseEntity;
import com.kuang.pojo.ApiParamsEntity;
import com.kuang.service.ApiHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiHistoryServiceImpl implements ApiHistoryService {

    @Autowired
    private ApiBaseMapper apiBaseMapper;
    @Autowired
    private ApiParamsMapper apiParamsMapper;

    @Override
    public List<ApiHistoryEntity> selectAllApis() {
        List<ApiHistoryEntity> apiHistoryEntities = new ArrayList<>();
        List<ApiBaseEntity> apiBaseEntities = apiBaseMapper.selectList(null);
        for (ApiBaseEntity entity :
                apiBaseEntities) {
            ApiHistoryEntity e = new ApiHistoryEntity();
            e.setData(entity);
            List<ApiParamsEntity> apiParamsEntities = apiParamsMapper.selectListByUrlId(e.getId());
            e.setApiHistoryParamsEntityList(apiParamsEntities);
            apiHistoryEntities.add(e);
        }
        return apiHistoryEntities;
    }

    @Override
    public int insertApi(ApiBaseEntity entity) {
        return apiBaseMapper.insert(entity);
    }

    @Override
    public void insertApiParams(List<ApiParamsEntity> entity) {
//        apiParamsMapper.insert()
    }

    @Override
    public int insertParam(ApiParamsEntity entity) {
        return apiParamsMapper.insert(entity);
    }

    @Override
    public List<ApiParamsEntity> selectListByUrlId(Integer id) {
        List<ApiParamsEntity> apiParamsEntities = new ArrayList<>();
        try {
            apiParamsEntities.addAll(apiParamsMapper.selectListByUrlId(id));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed -> " + e.getMessage());
        }
        return apiParamsEntities;
    }
}
