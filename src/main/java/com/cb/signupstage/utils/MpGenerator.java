package com.cb.signupstage.utils;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
/**
 * @author: ly
 * @time: 2020/11/13 9:57
 * @description:
 */
public class MpGenerator {

    static String packageName = "com.cb.signupstage"; // 当前包名
    static String author = "ly"; // 作者
    static String sqlUrl = "mysql://127.0.0.1:3306/"; // 数据库类型及地址
    static String sqlDb = "chamberlaindb"; // 数据库名
    static String sqlUser = "root";
    static String sqlPassword = "root";
    static String table = "paper_score_setting"; // 表，用逗号隔开

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:" + sqlUrl + sqlDb + "?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(sqlUser);
        dsc.setPassword(sqlPassword);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageName);
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
       // templateConfig.setXml(null); // 不生成MapperXML
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);

        strategy.setInclude(table.split(","));
        strategy.setControllerMappingHyphenStyle(true);
      //  strategy.setTablePrefix("t_"); // 表前缀，如t_user，没有就注释掉此行
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
