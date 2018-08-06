package com.example.demo.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author qsky on 2018/7/2
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionFactoryRef = "mySqlSessionFactory")
@EnableTransactionManagement
public class MySqlDataSourceConfig {

	@Bean(name = "mySqlDataSource")
	@Qualifier("mySqlDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.hikari.mysql")
	public DataSource mySqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public DataSourceTransactionManager masterManager() {
		return new DataSourceTransactionManager(mySqlDataSource());
	}

	@Bean
	@Primary
	public SqlSessionFactory mySqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(mySqlDataSource());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
		return bean.getObject();
	}

	@Bean
	@Primary
	public SqlSessionTemplate mySqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(mySqlSessionFactory());
	}
}
