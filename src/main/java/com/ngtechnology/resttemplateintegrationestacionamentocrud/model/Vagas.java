package com.ngtechnology.resttemplateintegrationestacionamentocrud.model;

public class Vagas {
    private Long idVaga;
    private Boolean disponivel;


    public Long getIdVaga() {
        return idVaga;
    }

    public void setIdvaga(Long idVaga) {
        this.idVaga = idVaga;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public Vagas withBuilderDisponivel(Boolean disponivel){
        setDisponivel(disponivel);
        return this;
    }
    public Vagas withBuilderVagasId(Long id){
        setIdvaga(id);
        return this;
    }

}
