package com.kuang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuang.pojo.VersionControlEntity;
import org.springframework.stereotype.Repository;

//@Mapper
@Repository
public interface VersionControlMapper extends BaseMapper<VersionControlEntity> {

    VersionControlEntity selectByVersionId(String id);
}
