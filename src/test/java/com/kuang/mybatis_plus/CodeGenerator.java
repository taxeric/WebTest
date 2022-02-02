package com.kuang.mybatis_plus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

//代码自动生成器
public class CodeGenerator {

    public static void main(String[] args) {
        //需要构建一个 代码自动生成嚣 对象
        AutoGenerator mpg = new AutoGenerator();

        //配置 GlobalConfig
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");//文件存放位置
        gc.setAuthor("chad");//设置作者
        gc.setOpen(false);//是否打开windows文件夹  否
        gc.setFileOverride(false);//是否覆盖原来的文件 否
        gc.setServiceName("%sService");//去Service的I前缀
        gc.setIdType(IdType.AUTO);//id生成策略
        gc.setDateType(DateType.ONLY_DATE);//仅仅只是日期类型
        gc.setSwagger2(true);//开启Swagger2模式
        mpg.setGlobalConfig(gc);

        //配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("mysql");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //包的配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.mieasy");
        pc.setModuleName("plus");
        pc.setEntity("entity");//模块名
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        /*重点*/
        strategy.setInclude("user");//你的数据库表名，也可以是多个用逗号分隔开(具体根据使用时需要而定)
//        strategy.setInclude("user","grade_role","grade_role");

        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略（下划线转驼峰命名）
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // 自动lombok模型@Accessors(chain = true) setter链式操作
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
        strategy.setLogicDeleteFieldName("deleted");//逻辑删除
        //自动填充策略
        ArrayList<TableFill> fills = new ArrayList<>();
        fills.add(new TableFill("create_time", FieldFill.INSERT));//gmt_create
        fills.add(new TableFill("update_time",FieldFill.INSERT_UPDATE));//gmt_modified
        strategy.setTableFillList(fills);
        strategy.setVersionFieldName("version");//乐观锁
        strategy.setRestControllerStyle(true); //restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true); //url中驼峰转连字符
//        strategy.setTablePrefix(pc.getModuleName() + "_"); //生成实体时去掉表前缀
        mpg.setStrategy(strategy);

        mpg.execute();//执行
    }
}
