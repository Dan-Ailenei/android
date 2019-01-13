package com.example.dan.myapplication.Entities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Probe {

    @SerializedName("pagina")
    @Expose
    private Integer pagina;
    @SerializedName("probe")
    @Expose
    private List<Proba> probe = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Probe() {
    }

    /**
     *
     * @param pagina
     * @param probe
     */
    public Probe(Integer pagina, List<Proba> probe) {
        super();
        this.pagina = pagina;
        this.probe = probe;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public List<Proba> getProbe() {
        return probe;
    }

    public void setProbe(List<Proba> probe) {
        this.probe = probe;
    }

    @Override
    public String toString() {
        return "Proba{" +
                "pagina=" + pagina +
                ", probe=" + probe +
                '}';
    }
}