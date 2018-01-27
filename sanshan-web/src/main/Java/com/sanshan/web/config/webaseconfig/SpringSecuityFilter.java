package com.sanshan.web.config.webaseconfig;

import com.sanshan.Application;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**继承该类{@link AbstractSecurityWebApplicationInitializer}
 * 会将SpringSecurity自动注册到web容器中
 *
 * 如果使用Spring framework 构建 将这里的注释打开 然后将{@link Application}的继承
 * {@link SpringBootServletInitializer}注释即可
 * 默认采用Spring Boot 构建应用程序
 *
 */
public class SpringSecuityFilter  /*extends  AbstractSecurityWebApplicationInitializer */ {

}
