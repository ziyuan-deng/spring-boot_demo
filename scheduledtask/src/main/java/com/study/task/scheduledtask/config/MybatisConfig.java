package com.study.task.scheduledtask.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * mybatis配置文件
 *
 * @author wubin
 * @date 2019/7/30
 **/
@Configuration
@EnableTransactionManagement
@AutoConfigureAfter({DruidConfig.class})
@ConfigurationProperties(prefix = "mybatis")
@MapperScan({"com.study.task.*.mapper"})
public class MybatisConfig {

    private Logger logger = LoggerFactory.getLogger(MybatisConfig.class);

    @Resource(name = "druidDataSource")
    private DataSource druidDataSource;

   /* @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;*/
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;
    @Value("${mybatis.config-location}")
    private String configLocation;

    @Bean
    //@ConditionalOnMissingBean
    @Primary
    public SqlSessionFactory sqlSessionFactory() {
        try {
            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(druidDataSource);
            //sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
            sessionFactory.setMapperLocations(resourcePatternResolver
                    .getResources(mapperLocations));
            sessionFactory.setConfigLocation(resourcePatternResolver
                    .getResource(configLocation));

            return sessionFactory.getObject();
        } catch (Exception e) {
            logger.warn("Could not confiure mybatis session factory");
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    // @ConditionalOnMissingBean
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(druidDataSource);
    }
}

