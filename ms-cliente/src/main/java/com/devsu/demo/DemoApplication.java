package com.devsu.demo;

import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = "com.devsu.demo")
public class DemoApplication {
	private final Environment env;

	public DemoApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {
		var app = new SpringApplication(DemoApplication.class);

		Environment env = app.run(args).getEnvironment();
		logApplicationStartup(env);
	}

	private static void logApplicationStartup(Environment env) {
		var protocol = "http";

		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}
		var hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}
		log.info("""
                
                ----------------------------------------------------------
                Application '{}' is running! Access URLs:
                Local:      {}://localhost:{}{}
                External:   {}://{}:{}{}
                Profile(s): {}
                ----------------------------------------------------------""",
				env.getProperty("spring.application.name"),
				protocol,
				serverPort,
				contextPath,
				protocol,
				hostAddress,
				serverPort,
				contextPath,
				env.getActiveProfiles());
	}
}
