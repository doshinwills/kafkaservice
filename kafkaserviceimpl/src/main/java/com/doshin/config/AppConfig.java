package com.doshin.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.doshin.service.hibernate","com.doshin.service.kafka.bao", 
	"com.doshin.service.kafka.dao", "com.doshin.service.kafka.processor.config",
	"com.doshin.service.kafka.processor.producer", "com.doshin.service.kafka.processor.consumer", 
	"com.doshin.service.kafka.callback"})
public class AppConfig{

}
