package org.youth.imovie.config;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageHelper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages={"org.youth.imovie"})
public class ApplicationContextConfig {

	@Bean
	public DataSource getDataSource(){
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass("com.mysql.jdbc.Driver");
			dataSource.setJdbcUrl("jdbc:mysql://192.168.0.109:3306/test");
			dataSource.setUser("root");
			dataSource.setPassword("root");
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return dataSource;
	}
	
	@Bean
	MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		//mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("org.youth.imovie.dao");
		return mapperScannerConfigurer;
	}
	
	@Bean//(name = "sqlSessionFactory")
	public SqlSessionFactoryBean getSqlSessionFactory(){//(DataSource dataSource){
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setTypeAliasesPackage("org.youth.imovie.pojo.entity");
		
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources;
		try {
			resources = resolver.getResources("classpath*:mybatis/dao/*Mapper.xml");
			sessionFactory.setMapperLocations(resources);
			Interceptor[] plugins = new Interceptor[]{pageHelper()};
			sessionFactory.setPlugins(plugins);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sessionFactory;
	}
	
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(){
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(getDataSource());
		return dataSourceTransactionManager;
	}
	
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		properties.setProperty("reasonable", "true");
		pageHelper.setProperties(properties);
		return pageHelper;
	}
}
