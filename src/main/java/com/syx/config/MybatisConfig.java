package com.syx.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by shiyongxiang on 16/9/24.
 */
@Configuration
@ImportResource("classpath:/qaProfile.xml")
@MapperScan(basePackages="com.syx.mapper")
public class MybatisConfig {
    private static final Logger log = LoggerFactory.getLogger(MybatisConfig.class);
    @Profile("dev")
    @Bean
    public DataSource devDataSource(){
        log.info("dev");
        BasicDataSource bds=new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/springmybatis");
        bds.setUsername("root");
        bds.setPassword("root");
        bds.setInitialSize(5);
        bds.setMaxActive(10);
        return bds;
    }

    @Profile("prod")
    @Bean(destroyMethod = "close")
    public  DataSource prodDataSource(){
        log.info("prod");
        BasicDataSource bds=new BasicDataSource();
        bds.setDriverClassName("com.mysql.jdbc.Driver");
        bds.setUrl("jdbc:mysql://127.0.0.1:3306/springmybatis");
        bds.setUsername("root");
        bds.setPassword("root111");
        bds.setInitialSize(5);
        bds.setMaxActive(10);
        return bds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource,ApplicationContext applicationContext) throws Exception {
        log.info("sqlsession");
        SqlSessionFactoryBean sessionFactoryBean=new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage("com.syx.domain");
//        sessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));

        SqlSessionFactory sqlSessionFactory = sessionFactoryBean.getObject();
        // It can be specified a Configuration instance directly without MyBatis XML configuration file.
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);// 开启驼峰映射
        sqlSessionFactory.getConfiguration().setCacheEnabled(true);
        sqlSessionFactory.getConfiguration().setLazyLoadingEnabled(true);
        sqlSessionFactory.getConfiguration().setAggressiveLazyLoading(false);
        sqlSessionFactory.getConfiguration().setLogImpl(Log4jImpl.class);// logImpl
        return sqlSessionFactory;
//        return sessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        log.info("dataSourceTranceMang");
        return new DataSourceTransactionManager(dataSource);
    }
}
