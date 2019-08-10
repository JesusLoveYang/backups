package com.bu.config;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class SessionFactoryConfig {

	@Autowired
	private DataSource dataSource;
	
	private static String mapperPath;
	@Value("${mapper_path}")
	public  void setMapperPath(String mapperPath) {

		SessionFactoryConfig.mapperPath = mapperPath;
	}
	
	// mybatis-config的配置路径
	private static String mybatisConfigFile;
	@Value("${mybatis_config_file}")
	public  void setMybatisConfigFile(String mybatisConfigFile) {
		SessionFactoryConfig.mybatisConfigFile = mybatisConfigFile;
	}
	
	@Value("${type_alias_package}")
	private String typeAliasesPackage = "com.bu.entity";
	/**
	 * 创建sqlSessionFactoryBean 实例，并且设置configtion 设置mapper 映射路径 设置 DataSource数据源
	 * 
	 * @throws Exception
	 */
	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		
		// 设置mybatis configuration 扫描路径
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
		
		// 添加mapper扫描路径
		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
		String packSearchPathString = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
		sqlSessionFactoryBean
				.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packSearchPathString));
		
		// 设置DataSource
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		// 实体类
		sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
		return sqlSessionFactoryBean;
	}

}
