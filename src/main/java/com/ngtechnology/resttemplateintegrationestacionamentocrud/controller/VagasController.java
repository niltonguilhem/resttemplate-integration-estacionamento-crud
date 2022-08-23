package com.ngtechnology.resttemplateintegrationestacionamentocrud.controller;

import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.Vagas;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.VagasRequest;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.VagasResponse;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.service.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
    @RequestMapping("/api/v1/estacionamento")
    public class VagasController {

        @Autowired
        private VagasService service;

    @GetMapping()
    public ResponseEntity<List<VagasResponse>> getAllVagas() {
        List<Vagas> vagasList = service.findAllVagas();
        List<VagasResponse> vagasResponseList = vagasList.stream().map(vagas -> new VagasResponse()
                .withBuilderVagasId(vagas.getIdVaga())
                .withBuilderDisponivel(vagas.getDisponivel())).collect(Collectors.toList());

        return new ResponseEntity<>(vagasResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagasResponse> getId(@PathVariable("id") Long id) {
        Vagas vagas = service.getVagasById(id);
        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagas.getIdVaga())
                .withBuilderDisponivel(vagas.getDisponivel());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<VagasResponse> postVagas(@RequestBody VagasRequest vagasRequest){

        Vagas vagas = service.save(new Vagas()
                .withBuilderDisponivel(vagasRequest.getDisponivel()));

        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagas.getIdVaga())
                .withBuilderDisponivel(vagas.getDisponivel());

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagasResponse> putVagas (@PathVariable("id")Long id,
                                                       @RequestBody VagasRequest vagasRequest){


        Vagas vagasUpdate = new Vagas()
                .withBuilderVagasId(id)
                .withBuilderDisponivel(vagasRequest.getDisponivel());


        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagasUpdate.getIdVaga())
                .withBuilderDisponivel(vagasUpdate.getDisponivel());

        Vagas vagasEntity = service.update(vagasUpdate,id);

        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}


