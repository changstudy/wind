package com.chang.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Mybatis主数据源db1配置
 * 多数据源配置依赖数据源配置
 */
@MapperScan(basePackages ="com.chang.activity.dao", sqlSessionTemplateRef  = "db1SqlSessionTemplate")
@Configuration
public class DataSource01 {

    @Value("${spring.datasource.db1.url}")
    private String url;
    @Value("${spring.datasource.db1.username}")
    private String username;
    @Value("${spring.datasource.db1.password}")
    private String password;
    @Value("${spring.datasource.db1.driver-class-name}")
    private String driverClassName;
    @Resource
    private PageInterceptor pageInterceptor;
 
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setFilters("stat");
        return dataSource;
    }
 
    //主数据源 db1数据源
    @Bean("db1SqlSessionFactory")
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("dataSource1") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.chang.activity.pojo");
        bean.setPlugins(pageInterceptor);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/activity/*.xml"));
        SqlSessionFactory sqlSessionFactory = bean.getObject();
        org.apache.ibatis.session.Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.setMapUnderscoreToCamelCase(true);
        return sqlSessionFactory;
    }
 
    @Bean(name = "db1TransactionManager")
    public DataSourceTransactionManager db1TransactionManager(@Qualifier("dataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
 
    @Bean(name = "db1SqlSessionTemplate")
    public SqlSessionTemplate db1SqlSessionTemplate(@Qualifier("db1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    // 创建事务管理器
    @Bean(name = "db1TransactionManager")
    public PlatformTransactionManager txManager(@Qualifier("dataSource1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}