package com.iyunr.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 * 数据库连接池配置
 * 此处实现了EnvironmentAware接口，可以在工程启动时获取到系统环境变量和application配置文件的变量。
 * 还有一种方式是采用注解的方式进行获取 @value("$(变量的key值)")
 * @author Administrator
 *
 */

@Configuration
//@EnableTransactionManagement
public class DataBaseConfiguration implements EnvironmentAware{
//	private Environment environment;
	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment environment) {
//		this.environment = environment;
		this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
	}
	
	@Bean(initMethod = "init", destroyMethod = "close")
	public DataSource druidDataSource(){
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setUrl(propertyResolver.getProperty("url"));
		druidDataSource.setUsername(propertyResolver.getProperty("username"));
		druidDataSource.setPassword(propertyResolver.getProperty("password"));
		druidDataSource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initialSize")));
		druidDataSource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("minIdle")));
		druidDataSource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("maxActive")));
		druidDataSource.setMaxWait(Long.valueOf(propertyResolver.getProperty("maxWait")));
		druidDataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));
		druidDataSource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("minEvictableIdleTimeMillis")));
		druidDataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));
		druidDataSource.setTestWhileIdle(Boolean.valueOf(propertyResolver.getProperty("testWhileIdle")));
		druidDataSource.setTestOnBorrow(Boolean.valueOf(propertyResolver.getProperty("testOnBorrow")));
		druidDataSource.setTestOnReturn(Boolean.valueOf(propertyResolver.getProperty("testOnReturn")));
		try {
			druidDataSource.setFilters(propertyResolver.getProperty("filters"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}
	
	@Bean
	public ServletRegistrationBean druidServlet(){
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
//		initParameters.put("loginUsername", "admin");
//		initParameters.put("loginPassword", "123456");
		//禁用html页面上的“reset all”功能
		initParameters.put("resetEnable", "true");
		//IP白名单（没有配置或者为空的话，则允许所有的访问）
//		initParameters.put("allow", "127.0.0.1");
		//IP黑名单
		initParameters.put("deny", "192.168.2.71");
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}
	
	@Bean
	public FilterRegistrationBean FilterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		//添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*");
		//忽略资源
		filterRegistrationBean.addInitParameter("exclusions", "*.js, *.gif, *.jpg, *.bmp, *.png, *.css, *.ico, /druid/*");
		return filterRegistrationBean;
	}
	
	@Bean(value="druid-stat-interceptor")
	public DruidStatInterceptor druidStatInterceptor(){
		DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
		return druidStatInterceptor;
	}
	
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setProxyTargetClass(true);
		//设置要监控的bean的id
//		beanNameAutoProxyCreator.setBeanNames("sysRoleMapper", "helloController");
		beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
		return beanNameAutoProxyCreator;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(druidDataSource());
	}
	
}
