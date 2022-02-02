package com.kuang.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insertFill start ...");
        this.setFieldValByName("createTime",new Date(), metaObject);
        this.setFieldValByName("updateTime",new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("insertFill end ...");
        this.setFieldValByName("updateTime",new Date(), metaObject);
    }
}
