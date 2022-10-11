package com.ngtechnology.resttemplateintegrationestacionamentocrud.service;

import com.ngtechnology.resttemplateintegrationestacionamentocrud.config.RestTemplateIntegration;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.Vagas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.*;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
;

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

    /*public Vagas save(Vagas vagas) {
        logger.info("m=save - status=start");
        HttpEntity requestEntity = new HttpEntity<>(vagas);
        URI uri = URI.create(host + path);
        ResponseEntity<Vagas> vagasEntity =
                restTemplate.exchange(uri,HttpMethod.POST,requestEntity, Vagas.class);
        logger.info("m=save - status=finish");
        return vagasEntity.getBody();
    }*/
    public Vagas save(Vagas vagas) {
        logger.info("m=save - status=start");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>("Partner", headers);
        URI uri = URI.create(host + path);
        ResponseEntity<Vagas> vagasEntity =
                restTemplate.exchange(uri, HttpMethod.POST, requestEntity, Vagas.class);
        logger.info("m=save - status=finish");
        return vagasEntity.getBody();
    }

    public Vagas update(Vagas vagas, Long id) {
        logger.info("m=update - status=start " + id);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Map<String, String> map = new HashMap<>();
        map.put("Partner", "Star_Park");
        HttpEntity<Void> response = restTemplate.postForEntity(host + path, map, Void.class);
        logger.info("m=update - status=finish " + id);
        if (((ResponseEntity<Void>) response).getStatusCode() == HttpStatus.OK) {
            System.out.println("Request Successful");
        } else {
            System.out.println("Request Failed");
        }
        return vagas;
    }

    /*public Vagas update(Vagas vagas, Long id) {
        logger.info("m=update - status=start " + id);
        HttpEntity requesEntity = new HttpEntity<>(vagas);
        URI uri = URI.create(host + path);
        ResponseEntity<Vagas> vagasEntity =
                restTemplate.exchange(uri,HttpMethod.PUT,requesEntity,Vagas.class);
        logger.info("m=update - status=finish " + id);
        return vagasEntity.getBody();
    }*/

}