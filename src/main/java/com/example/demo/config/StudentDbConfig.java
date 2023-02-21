package com.example.demo.config;

import com.example.demo.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackageClasses = StudentRepository.class ,entityManagerFactoryRef = "studentEntityManager")
public class StudentDbConfig
{
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "student.datasource")
    public DataSource studentDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean studentEntityManager(
            EntityManagerFactoryBuilder builder,
            @Qualifier("studentDataSource") DataSource dataSource)
    {
        LocalContainerEntityManagerFactoryBean factoryBean = builder.dataSource(dataSource).packages("com.example.demo.model.student").build();
        factoryBean.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "create-drop");
        return factoryBean;
    }
}
