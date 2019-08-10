package com.bu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bu.util.DESUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 配置DataSource到ioc容器里
 * 
 * @author sz
 *
 */
@Configuration   // 这个标签的作用：将下面的配置信息写入到IoC容器中
// 配置mybatis的扫描路径
@MapperScan("com.bu.dao")
public class DataSourceConfig {
	// 读取jdbc
	@Value("${jdbc.driver}")
	private String jdbcDriver;
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${jdbc.username}")
	private String jdbcUsername;
	@Value("${jdbc.password}")
	private String jdbcPassword;

	/**
	 * 生成与spring-dao.xml对应的DataSource
	 * @throws Exception 
	 */
	@Bean(name = "dataSource")
	public ComboPooledDataSource createDataSource() throws Exception {
		// 生成DataSource实例
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		// 设置数据库配置信息
		dataSource.setDriverClass(jdbcDriver);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(DESUtil.getDecryptString(jdbcUsername));
		dataSource.setPassword(DESUtil.getDecryptString(jdbcPassword));
		
		// 配置成c3p0连接池的私有属性
		dataSource.setMaxPoolSize(30);
		dataSource.setMinPoolSize(10);
		// 关闭后不自动commit
		dataSource.setAutoCommitOnClose(false); 
		dataSource.setCheckoutTimeout(10000);
		dataSource.setAcquireRetryAttempts(2);
		
		return dataSource;
	}
	
}
