package com.ngtechnology.resttemplateintegrationestacionamentocrud.model;

public class VagasResponse {
    private Long idVaga;
    private Boolean disponivel;



    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public VagasResponse withBuilderDisponivel(Boolean disponivel){
        setDisponivel(disponivel);
        return this;
    }
    public VagasResponse withBuilderVagasId(Long vagasId){
        setIdVaga(vagasId);
        return this;
    }
}
