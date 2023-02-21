package com.realblox.dimetime.control;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.realblox.dimetime")
@ComponentScan("com.realblox.dimetime")
public class AnomalyApplication {
	Logger logger_ = LoggerFactory.getLogger( AnomalyApplication.class );

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(AnomalyApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}
}
