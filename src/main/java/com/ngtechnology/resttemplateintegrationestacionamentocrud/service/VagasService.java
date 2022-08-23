package com.ngtechnology.resttemplateintegrationestacionamentocrud.service;

import com.ngtechnology.resttemplateintegrationestacionamentocrud.config.RestTemplateIntegration;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.Vagas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagasService {
    @Autowired

    private RestTemplateIntegration restTemplate;
    @Value("${api-estacionamento-crud.host}")
    private String host;
    @Value("${api-estacionamento-crud.path}")
    private String path;

    public List<Vagas> findAllVagas(){
        List<Vagas> vagas = restTemplate.exchange(host+path, HttpMethod.GET,null,new ParameterizedTypeReference<List<Vagas>>(){}).getBody();
        return vagas;
    }

    public Vagas getVagasById(Long id){
        Vagas vagas = restTemplate.getForObject(host+path+id,Vagas.class);
        return vagas;
    }

    public Vagas save(Vagas vagas){
        ResponseEntity<Vagas> vagasEntity =
                restTemplate.postForEntity(host+path,vagas,Vagas.class);
        return vagasEntity.getBody();
    }

    public Vagas update(Vagas vagas, Long id) {
        HttpEntity requesEntity = new HttpEntity<>(vagas);
        ResponseEntity<Vagas> vagasEntity =
                restTemplate.exchange(host+path+id,HttpMethod.PUT,requesEntity,Vagas.class);
        return vagasEntity.getBody();

    }


}
