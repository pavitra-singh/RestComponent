package com.myValuePack.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.myValuePack.util.BasicConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.myValuePack.persistence.service"})
@EnableJpaRepositories(basePackages = { "com.myValuePack.persistence.dao"})
//@PropertySource({ "classpath:database.properties" })
public class JPAConfiguration {

	/*@Autowired
	private Environment environment;*/

	public JPAConfiguration() {
		super();
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.myValuePack.persistence.model"});

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		HikariConfig dataSourceConfig = new HikariConfig();

		dataSourceConfig.setDriverClassName("com.mysql.jdbc.Driver");
		dataSourceConfig.setJdbcUrl("jdbc:mysql://localhost:3306/myvaluepack");
		dataSourceConfig.setUsername("root");
		dataSourceConfig.setPassword("root");
		dataSourceConfig.setIdleTimeout(0);
		dataSourceConfig.setMaxLifetime(0);
		dataSourceConfig.setMaximumPoolSize(20);
		dataSourceConfig.setMinimumIdle(10);

		return new HikariDataSource(dataSourceConfig);
	}


	@Bean(name = "transactionManager")
	public JpaTransactionManager transactionManager() {

		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return jpaTransactionManager;
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty(BasicConstants.HIBERNATE_SHOW_SQL,
				"true");
		hibernateProperties.setProperty(BasicConstants.HIBERNATE_DIALECT,
				"org.hibernate.dialect.MySQLDialect");
		hibernateProperties.setProperty(BasicConstants.HIBERNATE_FORMAT_SQL,
				"true");

		return hibernateProperties;
	}
}
