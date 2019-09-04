package br.com.devmedia.curso.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Com a anotação @EnableTransactionManagement. Informamos a o Spring que ative
 * o gerenciamento de transações, e assim passa a reconhecer o
 * TransactionManager.
 */
@Configuration
@EnableTransactionManagement
public class SpringJPAConfig {

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/armazenagem?autoReconnect=true");
		driverManagerDataSource.setUsername("armazem");
		driverManagerDataSource.setPassword("sysadm");
		return driverManagerDataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource){
        //Essa classe LocalCont... do Spring irá criar o recurso do EntityManagerFactory
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        //setPackagesToScan é aonde informanos, o local das clases que estão mapiadas pela persistence. As nossas entidades. 
        factoryBean.setPackagesToScan("br.com.devmedia.curso.entities");
        //setJpaVendorAdapter, informamos qual o frameword ORM estamos usando
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        transactionManager.setJpaDialect(new HibernateJpaDialect());
        return transactionManager;
    }

    private Properties jpaProperties(){
        Properties properties = new Properties();
		//properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
    
}