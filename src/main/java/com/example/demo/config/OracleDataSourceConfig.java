package com.example.demo.config;

import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author qsky on 2018/7/2
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "oracleEntityManagerFactory", transactionManagerRef = "oracleTransactionManager", basePackages = {
		"com.example.demo.repository"})
public class OracleDataSourceConfig {

	@Bean(name = "oracleDataSource")
	@Qualifier("oracleDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.hikari.oracle")
	public DataSource oracleDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Resource
	private JpaProperties jpaProperties;

	@Bean(name = "entityManager")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return oracleEntityManagerFactory(builder).getObject().createEntityManager();
	}

	/**
	 * 设置实体类所在位置
	 */
	@Bean(name = "oracleEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(oracleDataSource())
				.packages("com.example.demo.entity")
				.persistenceUnit("oraclePersistenceUnit")
				.properties(getProperties())
				.build();
	}

	private Map<String, Object> getProperties() {
		return jpaProperties.getHibernateProperties(new HibernateSettings());
	}

	@Bean(name = "oracleTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(oracleEntityManagerFactory(builder).getObject());
	}
}
