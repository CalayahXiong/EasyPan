package com.easypan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.easypan"})
@MapperScan(basePackages = {"com.easypan.mappers"})
@EnableTransactionManagement
@EnableScheduling

public class EasypanApplication {
    //private static final Logger logger = LoggerFactory.getLogger(CalayahPanApplication.class);

    public static void main(String[] args){
        SpringApplication.run(EasypanApplication.class,args);
    }

    @RequestMapping("/")
    public String hello(){
        return "Success load";
    }

//        logger.info("start to generate code...");
//        try {
//            //构建基础类??
//            BuildBaseJava.execute();
//
//            List<TableInfo> tableInfoList = new BuildTable().getTables();
//            for (TableInfo tableInfo : tableInfoList) {
//                //bean
//                BuildBeanPo.execuet(tableInfo);
//                //query
//                BuildBeanQuery.execute(tableInfo);
//                //mapper
//                BuildMapper.execute(tableInfo);
//                //service 定义接口函数
//                BuildService.execute(tableInfo);
//                //serviceImpl 接口函数的implementation
//                BuildServiceImpl.execute(tableInfo);
//                //controller
//                BuildController.execute(tableInfo);
//            }
//            logger.info("success" + PropertiesUtils.getString("path.base"));
//        } catch (Exception e) {
//            logger.error("failed:", e);
//        }
//
//    }
}
