package com.bgy.robot;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusGenerator {

    /**
     * 数据库配置
     */
    private String url = "jdbc:mysql://localhost:3306/robot?useUnicode=true&characterEncoding=UTF-8";
    private String driver = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "root";

    /**
     * 开发人员
     */
    private String author = "Judith";

    /**
     * 文件生成的目录
     * D:\documents\codeGen
     */
    private String javaHome = "D:\\documents\\codeGen";
    private String resourceHome = "D:\\documents\\codeGen\\resources\\mapper\\";

    /**
     * 生成的包名和类名
     */
    private String packageName = this.getClass().getPackage().getName();
    private String[] tableNames = {"order","delete_log"};

    /**
     * 设置包名 如mapper可以改为dao
     */
    private String mapperName = "mapper";

    @Test
    public void generatorCode() {
        generatorByTables(packageName,tableNames);
    }

    private void generatorByTables(String packageName,String... tableNames) {

        /**
         * 代码生成器
         */
        AutoGenerator mpg = new AutoGenerator().setGlobalConfig(
                // 全局配置
                new GlobalConfig()
                        .setActiveRecord(false) // 需要ActiveRecord特性 请改为true
                        .setAuthor(author) // 开发人员
                        .setOutputDir(javaHome) // 文件生成的目录
                        .setFileOverride(true) // 是否覆盖文件
                        .setEnableCache(false) // XML 二级缓存
                        .setBaseColumnList(true) // XML ColumnList
                        .setBaseResultMap(true) // XML ResultMap
                        .setServiceName("%sService") //自定义文件命名，%s 会自动填充表实体属性
        ).setDataSource(
                // 数据源配置
                new DataSourceConfig()
                        .setDbType(DbType.MYSQL) // 数据库类型
                        .setUrl(url)
                        .setDriverName(driver)
                        .setUsername(username)
                        .setPassword(password)
        ).setStrategy(
                // 策略配置
                new StrategyConfig()
                        .setCapitalMode(true) // 全局大写命名
                        .setEntityLombokModel(false) // Lombok 简化代码
                        .setNaming(NamingStrategy.underline_to_camel) // 表名生成策略
                        .setInclude(tableNames) // 修改成需要的表名，多个表名可传数组
        ).setPackageInfo(
                // 包设置
                new PackageConfig()
                        .setParent(packageName) // 自定义包路径
                        .setMapper(mapperName) // 数据层包名
        ).setCfg(
                // 注入自定义配置
                new InjectionConfig() {
                    @Override
                    public void initMap() {

                    }
                }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(
                        "/templates/mapper.xml.vm") { // 这段没有效果，但又不能没有这段
                    // 自定义 xxMapper.xml 文件输出目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return resourceHome + tableInfo.getEntityName() + "Mapper.xml";
                    }
                }))
        ).setTemplate(
                // 关闭默认 xml 生成，调整生成至根目录
                new TemplateConfig().setXml(null)
        );
        mpg.execute();
    }
}
