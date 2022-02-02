package com.kuang.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //名字邮箱不为空，年龄大于等于12岁
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.isNotNull("name").isNotNull("email").ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void testSelectOne() {
        //名字为teddy
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","teddy");
        System.out.println(userMapper.selectOne(wrapper));
    }

    @Test
    void testBetween() {
        //名字在20岁到30岁之间的
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20, 30);
        System.out.println(userMapper.selectCount(wrapper));
    }

    @Test
    void testLike() {
        //名字不包含B以S为开头
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","B").likeRight("name","S");
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    @Test
    void testSelectObject() {
        //名字不包含B以S为开头
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.notLike("name","B").likeRight("name","S");
        userMapper.selectMaps(wrapper).forEach(System.out::println);
    }

    @Test
    void testInSql() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.inSql("id","select id from user where id < 5");
        userMapper.selectObjs(wrapper).forEach(System.out::println);
    }

    @Test
    void testOrder() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
