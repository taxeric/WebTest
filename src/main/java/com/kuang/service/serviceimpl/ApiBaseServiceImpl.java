package com.kuang.service.serviceimpl;

import com.kuang.dto.ApiHistoryEntity;
import com.kuang.mapper.ApiBaseMapper;
import com.kuang.pojo.ApiBaseEntity;
import com.kuang.service.ApiBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiBaseServiceImpl implements ApiBaseService {

    @Autowired
    private ApiBaseMapper apiBaseMapper;

    @Override
    public List<ApiHistoryEntity> selectAllApis() {
        List<ApiBaseEntity> apiBaseEntities = apiBaseMapper.selectList(null);
        return null;
    }
}
