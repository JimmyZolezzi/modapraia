package moda.praia.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

@Configuration
@ComponentScan({"moda.praia.modulo","moda.praia.modulo.produtos","moda.praia.modulo.produtos.dao"})
@ImportResource("classpath:config-jpa-spring.xml")
public class JPAConfig {

	/*

	@Bean(name="entityManagerFactory")
	@Autowired
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(DataSource datasource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setDataSource(datasource);
		lcemfb.setPersistenceUnitName("viviModaPraiaPersistenceUnit");
		LoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
		lcemfb.setLoadTimeWeaver(loadTimeWeaver);
		lcemfb.setJpaVendorAdapter(jpaVendorAdapter);
		return lcemfb;
	}

	@Bean(name="datasource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/sunvibesbd?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		// I'm using MySQL5InnoDBDialect to make my tables support foreign keys
		return adapter;
	}

	
	@Bean(name="transactionManager")
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {	
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(entityManagerFactory);
		tm.setTransactionSynchronization(AbstractPlatformTransactionManager.SYNCHRONIZATION_ALWAYS);
		tm.setDataSource(getDataSource());
		return new JpaTransactionManager(entityManagerFactory);
	}
*/
}
