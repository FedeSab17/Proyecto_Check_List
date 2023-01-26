
package Modelo;

import java.time.LocalDate;


public class Conclusion {
    
    private Long idConclusion;
    private String obraTerminada;
    private double avanceActual;
    private double avanceEsperado;
    private LocalDate fechaFinalizacion;
    private int gradoSatisfaccion;
    private String comentario;
    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private String estado;
    private Long idGeneral;
    
    //Variable de relacion bidireccional Conclusion:General (1:1) =>
    private General general;

    public Conclusion() {
    }

    public Conclusion(Long idConclusion, String obraTerminada, double avanceActual, double avanceEsperado, LocalDate fechaFinalizacion, int gradoSatisfaccion, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idGeneral) {
        this.idConclusion = idConclusion;
        this.obraTerminada = obraTerminada;
        this.avanceActual = avanceActual;
        this.avanceEsperado = avanceEsperado;
        this.fechaFinalizacion = fechaFinalizacion;
        this.gradoSatisfaccion = gradoSatisfaccion;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idGeneral = idGeneral;
    }

    public Conclusion(String obraTerminada, double avanceActual, double avanceEsperado, LocalDate fechaFinalizacion, int gradoSatisfaccion, String comentario, LocalDate fechaAlta, LocalDate fechaBaja, String estado, Long idGeneral) {
        this.obraTerminada = obraTerminada;
        this.avanceActual = avanceActual;
        this.avanceEsperado = avanceEsperado;
        this.fechaFinalizacion = fechaFinalizacion;
        this.gradoSatisfaccion = gradoSatisfaccion;
        this.comentario = comentario;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.estado = estado;
        this.idGeneral = idGeneral;
    }

    public Long getIdConclusion() {
        return idConclusion;
    }

    public void setIdConclusion(Long idConclusion) {
        this.idConclusion = idConclusion;
    }

    public String getObraTerminada() {
        return obraTerminada;
    }

    public void setObraTerminada(String obraTerminada) {
        this.obraTerminada = obraTerminada;
    }

    public double getAvanceActual() {
        return avanceActual;
    }

    public void setAvanceActual(double avanceActual) {
        this.avanceActual = avanceActual;
    }

    public double getAvanceEsperado() {
        return avanceEsperado;
    }

    public void setAvanceEsperado(double avanceEsperado) {
        this.avanceEsperado = avanceEsperado;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getGradoSatisfaccion() {
        return gradoSatisfaccion;
    }

    public void setGradoSatisfaccion(int gradoSatisfaccion) {
        this.gradoSatisfaccion = gradoSatisfaccion;
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

    public Long getIdGeneral() {
        return idGeneral;
    }

    public void setIdGeneral(Long idGeneral) {
        this.idGeneral = idGeneral;
    }
    
    public General getGeneral() {
        return general;
    }

    public void setGeneral(General general) {
        this.general = general;
    }

    @Override
    public String toString() {
        return "idConclusion: " + idConclusion + "\nobraTerminada: " + obraTerminada + "\navanceActual: " + avanceActual + "\navanceEsperado: " + avanceEsperado + 
                "\nfechaFinalizacion: " + fechaFinalizacion + "\ngradoSatisfaccion: " + gradoSatisfaccion + "\ncomentario: " + comentario + 
                "\nfechaAlta: " + fechaAlta + "\nfechaBaja: " + fechaBaja + "\nestado: " + estado + "\nidGeneral: " + idGeneral;
    }

    

}
