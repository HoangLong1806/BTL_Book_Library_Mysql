package com.example.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.retry.annotation.EnableRetry;

import com.example.bookservice.config.AxonConfig;

@SpringBootApplication
@EnableDiscoveryClient
@Import({ AxonConfig.class })
@ComponentScan({"com.example.bookservice", "com.example.commonservice"})
@EnableRetry
public class BookserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}

}
