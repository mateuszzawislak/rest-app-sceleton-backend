package rest.app.sceleton.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataAccessConfig {

	@Value("${datasource.db.url}")
	String jdbcUrl;

	@Value("${datasource.db.username}")
	String jdbcUsername;

	@Value("${datasource.db.password}")
	String jdbcPassword;

	@Value("${datasource.db.driver}")
	String jdbcDriver;

	@Value("${datasource.hibernate.dialect}")
	private String hibernateDialect;

	@Value("${datasource.hibernate.show_sql}")
	private String hibernateShowSql;

	@Value("${datasource.hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;

	@Value("${entitymanager.packagesToScan}")
	private String entitymanagerPackagesToScan;

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		emf.setPackagesToScan(entitymanagerPackagesToScan);

		Properties jpaProperties = new Properties();
		jpaProperties.put("hibernate.show_sql", hibernateShowSql);
		jpaProperties.put("hibernate.dialect", hibernateDialect);
		jpaProperties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);

		emf.setJpaProperties(jpaProperties);

		return emf;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(jdbcUrl, jdbcUsername, jdbcPassword);
		dataSource.setDriverClassName(jdbcDriver);
		return dataSource;
	}

}