package com.ngtechnology.resttemplateintegrationestacionamentocrud.controller;

import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.Vagas;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.VagasRequest;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.model.VagasResponse;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.service.VagasService;
import com.ngtechnology.resttemplateintegrationestacionamentocrud.utils.VagasUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/estacionamento")
@ControllerAdvice
public class VagasController {

    private static final Logger logger = LoggerFactory.getLogger(VagasController.class);

    @Autowired
        private VagasService service;

    @GetMapping()
    public ResponseEntity<List<VagasResponse>> getAllVagas() {
        logger.info("m=getAllVagas - status=start");
        List<Vagas> vagasList = service.findAllVagas();
        List<VagasResponse> vagasResponseList = vagasList.stream().map(vagas -> new VagasResponse()
                .withBuilderVagasId(vagas.getIdVaga())
                .withBuilderDisponivel(vagas.getDisponivel())).collect(Collectors.toList());
        logger.info("m=getAllVagas - status=finish");
        return new ResponseEntity<>(vagasResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagasResponse> getIdVaga(@PathVariable("id") Long id) {
        try {

            logger.info("m=getIdVaga - status=start " + id);
            Vagas vagas = service.getVagaById(id);
            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagas.getIdVaga())
                    .withBuilderDisponivel(vagas.getDisponivel());
            logger.info("m=getIdVaga - status=finish " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
            }catch (NoSuchElementException idNull) {
            logger.warn("m=getIdVaga - status=warn " + id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @PostMapping
    public ResponseEntity<VagasResponse> postVagas(@RequestBody VagasRequest vagasRequest,
                                                   @RequestHeader String partner) throws Exception {
        VagasUtils.validatedHeader(partner);
        ResponseEntity<VagasResponse> result;
        logger.info("m=postVagas - status=start " + partner);
        Vagas vagas = service.save(new Vagas()
                .withBuilderDisponivel(vagasRequest.getDisponivel()),partner);

        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagas.getIdVaga())
                .withBuilderDisponivel(vagas.getDisponivel());

        result = new ResponseEntity<>(response,HttpStatus.CREATED);
        logger.info("m=postVagas - status=finish " + partner);
        return result;
    }
    @PutMapping("/{id}")
    public ResponseEntity<VagasResponse> putVagas (@PathVariable("id")Long id,
                                                   @RequestBody VagasRequest vagasRequest,
                                                   @RequestHeader String partner) throws Exception {
        VagasUtils.validatedHeader(partner);
        logger.info("m=putVagas - status=start " + id + " " + partner);
        Vagas vagasUpdate = new Vagas()
                .withBuilderVagasId(id)
                .withBuilderDisponivel(vagasRequest.getDisponivel());

        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagasUpdate.getIdVaga())
                .withBuilderDisponivel(vagasUpdate.getDisponivel());

        Vagas vagasEntity = service.update(vagasUpdate,id,partner);
        logger.info("m=putVagas - status=finish " + id + " " + partner);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}


