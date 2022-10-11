package com.ngtechnology.resttemplateintegrationestacionamentocrud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResttemplateIntegrationEstacionamentoCrudApplication {
	private static Logger logger = LoggerFactory.getLogger(ResttemplateIntegrationEstacionamentoCrudApplication.class);
	public static void main(String[] args) {
		logger.info("Iniciando API controle de vagas");
		SpringApplication.run(ResttemplateIntegrationEstacionamentoCrudApplication.class, args);
		logger.info("API de controle de vaga iniciada e pronta para receber requisições");
	}

}
