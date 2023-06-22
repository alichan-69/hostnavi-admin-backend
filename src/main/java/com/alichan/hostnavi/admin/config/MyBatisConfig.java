package com.alichan.hostnavi.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.alichan.hostnavi.admin.infrastracture.mapper.generated",
    "com.alichan.hostnavi.admin.infrastracture.mapper.custom"})
public class MyBatisConfig {
}
