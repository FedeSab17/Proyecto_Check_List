
package Modelo;

import java.time.LocalDate;



public class Panel {
    
    private Long idPanel;
    private String selladores;
    private String izaje;
    private String tornillos;
    private String perfileria;
    private String panelesFrio;
    private String perfileriaFrio;
    private double espesor;
    private String resultado;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idVisita;
    
    //Variable de relacion bidireccional Visita:Panel (1:1) =>
    private Visita visita;

    public Panel() {
    }

    public Panel(Long idPanel, String selladores, String izaje, String tornillos, String perfileria, String panelesFrio, String perfileriaFrio, double espesor, String resultado, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.idPanel = idPanel;
        this.selladores = selladores;
        this.izaje = izaje;
        this.tornillos = tornillos;
        this.perfileria = perfileria;
        this.panelesFrio = panelesFrio;
        this.perfileriaFrio = perfileriaFrio;
        this.espesor = espesor;
        this.resultado = resultado;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Panel(String selladores, String izaje, String tornillos, String perfileria, String panelesFrio, String perfileriaFrio, double espesor, String resultado, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.selladores = selladores;
        this.izaje = izaje;
        this.tornillos = tornillos;
        this.perfileria = perfileria;
        this.panelesFrio = panelesFrio;
        this.perfileriaFrio = perfileriaFrio;
        this.espesor = espesor;
        this.resultado = resultado;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Long getIdPanel() {
        return idPanel;
    }

    public void setIdPanel(Long idPanel) {
        this.idPanel = idPanel;
    }

    public String getSelladores() {
        return selladores;
    }

    public void setSelladores(String selladores) {
        this.selladores = selladores;
    }

    public String getIzaje() {
        return izaje;
    }

    public void setIzaje(String izaje) {
        this.izaje = izaje;
    }

    public String getTornillos() {
        return tornillos;
    }

    public void setTornillos(String tornillos) {
        this.tornillos = tornillos;
    }

    public String getPerfileria() {
        return perfileria;
    }

    public void setPerfileria(String perfileria) {
        this.perfileria = perfileria;
    }

    public String getPanelesFrio() {
        return panelesFrio;
    }

    public void setPanelesFrio(String panelesFrio) {
        this.panelesFrio = panelesFrio;
    }

    public String getPerfileriaFrio() {
        return perfileriaFrio;
    }

    public void setPerfileriaFrio(String perfileriaFrio) {
        this.perfileriaFrio = perfileriaFrio;
    }

    public double getEspesor() {
        return espesor;
    }

    public void setEspesor(double espesor) {
        this.espesor = espesor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDate fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(Long idVisita) {
        this.idVisita = idVisita;
    }
    
    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }

    @Override
    public String toString() {
        return "idPanel: " + idPanel + "\nselladores: " + selladores + "\nizaje: " + izaje + "\ntornillos: " + tornillos + "\nperfileria: " + perfileria + 
                "\npanelesFrio: " + panelesFrio + "\nperfileriaFrio: " + perfileriaFrio + "\nespesor: " + espesor + "\nresultado: " + resultado + 
                "\ncomentario: " + comentario + "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + "\nestado: " + estado + "\nidVisita: " + idVisita;
    }


}
