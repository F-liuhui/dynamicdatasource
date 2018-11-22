package com.example.demo.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 *    springboot内嵌的web容器配置
 * 
 * @author lh
 *
 */
@Configuration
public class EmbeddedWebServerConfig {
	@Bean
	public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> add(){
		return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
			@Override
			public void customize(ConfigurableServletWebServerFactory factory) {
				//配置内嵌tomcat的相关参数
				((TomcatServletWebServerFactory)factory).addConnectorCustomizers(new TomcatConnectorCustomizer() {
					@Override
					public void customize(Connector connector) {
						Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
						//最大连接数
		                protocol.setMaxConnections(200);
		                //最大线程数
		                protocol.setMaxThreads(200);
		                //最大选择者数，一般和最大线程数保持一致
		                protocol.setSelectorTimeout(200);
		                //session失效时间
		                protocol.setSessionTimeout(30000);
		                //连接超时时间
		                protocol.setConnectionTimeout(60000);
					}
		        });
			}
		};
	}
}
