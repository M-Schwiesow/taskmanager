package com.mws.examtwo;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExamtwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamtwoApplication.class, args);
	}
	

@Bean
public ServletWebServerFactory servletContainer() {
    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
    Connector ajpConnector = new Connector("AJP/1.3");
    ajpConnector.setPort(9090);
    ajpConnector.setSecure(false);
    ajpConnector.setAllowTrace(false);
    ajpConnector.setScheme("http");
    tomcat.addAdditionalTomcatConnectors(ajpConnector);
    return tomcat;
}
}
