package com.power.assistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("com.power.assistant.mapper")
//@PropertySource({"file:/usr/local/assistant/config/app.properties"})
@PropertySource({"file:/Users/jaydenwu/Documents/Home/app.properties"})
public class AssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}
}
