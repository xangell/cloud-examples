package de.demo.cicd;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class Config {
	
	@Value("${de.demo.cicd.service}")
	public String service;
}
