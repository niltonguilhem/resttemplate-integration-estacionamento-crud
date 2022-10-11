package com.ngtechnology.resttemplateintegrationestacionamentocrud.config;

import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.Vagas;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class RestTemplateIntegration extends RestTemplate {

    @Bean
    public RestTemplate restTemplate() {return new RestTemplate();}


}
