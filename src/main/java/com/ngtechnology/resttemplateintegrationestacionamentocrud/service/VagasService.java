package com.ngtechnology.resttemplateintegrationestacionamentocrud.service;

import com.ngtechnology.resttemplateintegrationestacionamentocrud.config.RestTemplateIntegration;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.Vagas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class VagasService {
    private static final Logger logger = LoggerFactory.getLogger(VagasService.class);
    @Autowired
    private RestTemplateIntegration restTemplate;
    @Value("${api-estacionamento-crud.host}")
    private String host;
    @Value("${api-estacionamento-crud.path}")
    private String path;
    @Value("${api-estacionamento-crud.path-id}")
    private String pathId;

    public List<Vagas> findAllVagas() {
        logger.info("m=findAllVagas - status=start");
        URI uri = UriComponentsBuilder.fromHttpUrl(host + path)
                .build().toUri();
        List<Vagas> vagas = restTemplate.exchange(uri,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Vagas>>() {
                }).getBody();
        logger.info("m=findAllVagas - status= finish");
        return vagas;
    }
    public Vagas getVagaById(Long id) {
        logger.info("m=getVagaById - status=start " + id);
        URI uri = UriComponentsBuilder.fromHttpUrl(host + path + id)
                .build().toUri();
        Vagas vagas = restTemplate.exchange(uri.toString(), HttpMethod.GET, null, Vagas.class, id).getBody();
        logger.info("m=getVagaById - status=finish " + id);
        return vagas;
    }
    public Vagas save(Vagas vagas, String partner) {
        logger.info("m=save - status=start");
        HttpHeaders headers = new HttpHeaders();
        headers.add("partner", partner );
        HttpEntity<Vagas> entity = new HttpEntity<>(vagas,headers);
        URI uri = URI.create(host + path);
        ResponseEntity<Vagas> vagasEntity =
                restTemplate.exchange(uri, HttpMethod.POST,entity,Vagas.class);
        logger.info("m=save - status=finish");
        return vagasEntity.getBody();
    }
    public Vagas update(Vagas vagas, Long id, String partner) {
        logger.info("m=update - status=start " + id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("partner", partner);
        HttpEntity<Vagas> entity = new HttpEntity<>(vagas,headers);
        URI uri = URI.create(host + path + id);
        ResponseEntity<Vagas> vagasEntity =
                restTemplate.exchange(uri,HttpMethod.PUT,entity,Vagas.class);
        logger.info("m=update - status=finish " + id);
        return vagasEntity.getBody();
    }

}