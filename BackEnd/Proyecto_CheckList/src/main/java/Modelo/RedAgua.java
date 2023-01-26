
package Modelo;

import java.time.LocalDate;


public class RedAgua {
    
    private Long idAgua;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private double metrosLineales;
    private int nroPersonas;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idVisita;
    
    //Variable de relacion bidireccional Visita:Panel (1:1) =>
    private Visita visita;
    

    public RedAgua() {
    }

    public RedAgua(Long idAgua, LocalDate fechaInicial, LocalDate fechaFinal, double metrosLineales, int nroPersonas, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.idAgua = idAgua;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.metrosLineales = metrosLineales;
        this.nroPersonas = nroPersonas;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public RedAgua(LocalDate fechaInicial, LocalDate fechaFinal, double metrosLineales, int nroPersonas, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.metrosLineales = metrosLineales;
        this.nroPersonas = nroPersonas;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Long getIdAgua() {
        return idAgua;
    }

    public void setIdAgua(Long idAgua) {
        this.idAgua = idAgua;
    }

    public LocalDate getFechaInicio() {
        return fechaInicial;
    }

    public void setFechaInicio(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getMetrosLineales() {
        return metrosLineales;
    }

    public void setMetrosLineales(double metrosLineales) {
        this.metrosLineales = metrosLineales;
    }

    public int getNroPersonas() {
        return nroPersonas;
    }

    public void setNroPersonas(int nroPersonas) {
        this.nroPersonas = nroPersonas;
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
        return "idAgua: " + idAgua + "\nfechaInicial: " + fechaInicial + "\nfechaFinal: " + fechaFinal + "\nmetrosLineales: " + metrosLineales + 
                "\nnroPersonas: " + nroPersonas + "\ncomentario: " + comentario + "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + 
                "\nestado: " + estado + "\nidVisita: " + idVisita;
    }
    
    
    
}
