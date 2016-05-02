package org.youth.imovie.config;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextConfigTest {

	@Test
	public  void testDataSource(){
		AnnotationConfigApplicationContext ctx = 
				   new AnnotationConfigApplicationContext();
				   ctx.register(ApplicationContextConfig.class);
				   ctx.refresh();
				   DataSource  dataSource = ctx.getBean(DataSource.class);
				   Assert.assertNotNull(dataSource);
				   
				   SqlSessionFactoryBean  sqlSessionFactoryBean = ctx.getBean(SqlSessionFactoryBean.class);
				   Assert.assertNotNull(sqlSessionFactoryBean);
	}
	
}
