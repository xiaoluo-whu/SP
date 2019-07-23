package com.bnq.supplier.sp.dao.plugin;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.bnq.supplier.sp.dao.mapper")
public class DruidDataSourceConfig implements EnvironmentAware {

    private Environment environment;
    private Binder binder;

    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.binder = Binder.get(environment);
    }

    //注册dataSource
    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource druidDataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setDriverClassName(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getDriverClassName());
        druidDataSource.setUrl(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getUrl());
        druidDataSource.setUsername(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getUsername());
        druidDataSource.setPassword(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getPassword());
        druidDataSource.setInitialSize(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getInitialSize());
        druidDataSource.setMinIdle(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getMinIdle());
        druidDataSource.setMaxActive(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getMaxActive());
        druidDataSource.setMaxWait(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getValidationQuery());
        druidDataSource.setTestWhileIdle(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().isTestWhileIdle());
        druidDataSource.setTestOnBorrow(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().isTestOnBorrow());
        druidDataSource.setTestOnReturn(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getMaxPoolPreparedStatementPerConnectionSize());
        druidDataSource.setProxyFilters(binder.bind("spring.datasource", Bindable.of(DruidDataSource.class)).get().getProxyFilters());


        System.out.println("my datadruid output" + druidDataSource.getMaxPoolPreparedStatementPerConnectionSize());

        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSource());
        //mybatis分页
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props); //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/bnq/supplier/sp/dao/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(druidDataSource());
    }
}