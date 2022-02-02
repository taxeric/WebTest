package com.kuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.ApiParamsEntity;
import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Mapper
@Repository
public interface ApiParamsMapper extends BaseMapper<ApiParamsEntity> {

    List<ApiParamsEntity> selectListByUrlId(Integer id);
}
