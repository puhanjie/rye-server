package com.puhj.rye;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author puhanjie
 * @description 代码生成器
 * @create 2022-11-28
 */
public class CodeGenerator {

    public static void main(String[] args) {
        /* 数据库连接信息 */
        String url = "jdbc:mysql://localhost:3306/rye?serverTimezone=UTC&characterEncoding=UTF8";
        String username = "root";
        String password = "root123456";

        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("puhanjie")  // 设置作者
                            .enableSwagger()    // 开启Swagger
                            .outputDir(projectPath + "/src/main/java");  // 指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com.puhj.rye") // 设置父包名
                            .entity("entity")   // 设置entity包名
                            .mapper("mapper")   // 设置mapper包名
                            .xml("mapper")  // 设置mapper.xml包名
                            .service("service") // 设置service包名
                            .serviceImpl("service.impl")    // 设置serviceImpl包名
                            .controller("controller.api.v1")   // 设置controller包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper")); // 设置mapper.xml文件生成路径
                })
                .strategyConfig((scanner, builder) ->
                        builder.addInclude(getTables(scanner.apply("请输入表名，多个表用英文逗号分隔（所有输入 all）："))) // 设置需要生成的表名
                                .controllerBuilder()    // controller策略配置
                                .enableRestStyle()  // controller开启生成@RestController控制器
                                .formatFileName("%sController") // controller文件名格式化
                                .serviceBuilder()   // service策略配置
                                .formatServiceFileName("%sService")    // Service文件名称格式化
                                .formatServiceImplFileName("%sServiceImpl") // Service实现类文件名格式化
                                .mapperBuilder()    // mapper策略配置
                                .formatMapperFileName("%sMapper")   // mapper文件名格式化
                                .formatXmlFileName("%sMapper")  // mapper.xml文件名格式化
                                .enableMapperAnnotation()   // 开启@Mapper注解
                                .entityBuilder()    // entity策略配置
                                .enableLombok() // 开启lombok注解
                                .enableTableFieldAnnotation()    // 开启TableFiled注解
                                .build()
                )
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
