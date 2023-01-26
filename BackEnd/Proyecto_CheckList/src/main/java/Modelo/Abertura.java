
package Modelo;

import java.time.LocalDate;


public class Abertura {
    
    private Long idAbertura;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private String tipoAbertura;
    private int cantidad;
    private double m2;
    private int nroPersona;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idVisita;
    
    //Variable de relacion bidireccional Visita:Panel (1:1) =>
    private Visita visita;

    public Abertura() {
    }

    public Abertura(Long idAbertura, LocalDate fechaInicial, LocalDate fechaFinal, String tipoAbertura, int cantidad, double m2, int nroPersona, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.idAbertura = idAbertura;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tipoAbertura = tipoAbertura;
        this.cantidad = cantidad;
        this.m2 = m2;
        this.nroPersona = nroPersona;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Abertura(LocalDate fechaInicial, LocalDate fechaFinal, String tipoAbertura, int cantidad, double m2, int nroPersona, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idVisita) {
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.tipoAbertura = tipoAbertura;
        this.cantidad = cantidad;
        this.m2 = m2;
        this.nroPersona = nroPersona;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idVisita = idVisita;
    }

    public Long getIdAbertura() {
        return idAbertura;
    }

    public void setIdAbertura(Long idAbertura) {
        this.idAbertura = idAbertura;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getM2() {
        return m2;
    }

    public void setM2(double m2) {
        this.m2 = m2;
    }

    public int getNroPersona() {
        return nroPersona;
    }

    public void setNroPersona(int nroPersona) {
        this.nroPersona = nroPersona;
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
    
    public String getTipoAbertura() {
        return tipoAbertura;
    }

    public void setTipoAbertura(String tipoAbertura) {
        this.tipoAbertura = tipoAbertura;
    }

    @Override
    public String toString() {
        return "idAbertura: " + idAbertura + "\nfechaInicial: " + fechaInicial + "\nfechaFinal: " + fechaFinal + "\ntipoAbertura: " + tipoAbertura + "\ncantidad: " + cantidad + "\nm2: " + m2 + 
                "\nnroPersona: " + nroPersona + "\ncomentario: " + comentario + "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + 
                "\nestado: " + estado + "\nidVisita: " + idVisita;
    }

    
    
    
    
}
